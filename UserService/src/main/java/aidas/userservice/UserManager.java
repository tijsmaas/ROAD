/*
 * Copyright by AIDaS.
 */

package aidas.userservice;

import aidas.security.Security;
import aidas.userservice.converters.UserConverter;
import aidas.userservice.dto.Right;
import aidas.userservice.dto.UserDto;
import aidas.userservice.entities.UserEntity;
import aidas.userservice.exceptions.UserSystemException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

/**
 * The class representing the implementation of the {@link IUserManager}.
 * 
 * @author Geert
 */
public class UserManager implements IUserManager {
    
    /**
     * The entity manager of the database.
     */
    private EntityManager em;

    /**
     * Create a new instance of the {@link UserManager} class.
     * @param emf the {@link EntityManagerFactory} from which the {@link EntityManager} can be created.
     */
    public UserManager(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }
    
    @Override
    public void demandRight(String authId, Right right) throws UserSystemException {
        if (!this.verifyRight(authId, right)) {
            throw new UserSystemException("The user doesn't have to provided right.");
        }
    }

    @Override
    public UserDto login(String username, String password) throws UserSystemException {
        UserEntity user = this.em.createQuery("SELECT u FROM USERS u WHERE u.username = :username", UserEntity.class)
                .setParameter("username", username)
                .getSingleResult();

        UserDto userDto = null;
            
        try {
            if (user!= null && user.getPassword().equals(Security.processPassword(password, username, user.getSalt()))) {
                userDto = UserConverter.toUserDto(user);
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException 
                | InvalidKeyException | IllegalBlockSizeException 
                | BadPaddingException | InvalidKeySpecException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            
            throw new UserSystemException(ex.getMessage());
        }
            
        return userDto;
    }

    @Override
    public void register(String username, String password) throws UserSystemException {
        if (username == null || username.isEmpty() || username.length() > 28) {
            throw new UserSystemException("The username is mandatory and can have a maximum length of 28 characters.");
        } else if (password == null || password.isEmpty() || password.length() < 8 || password.length() > 4000) {
            throw new UserSystemException("The password is mandatory and must have a length between 8 - 4000 characters.");
        }
            
        try {
            byte[] salt = Security.generateSalt();
            String processedPassword = Security.processPassword(password, username, salt);
            
            em.persist(new UserEntity(username, processedPassword, salt));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException 
                | InvalidKeyException | IllegalBlockSizeException 
                | BadPaddingException | InvalidKeySpecException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            
            throw new UserSystemException(ex.getMessage());
        }
    }

    @Override
    public boolean verifyRight(String username, Right right) {
        return this.em.createQuery("SELECT COUNT(u) FROM USERS u WHERE u.username = :username AND (:right IN(u.rights) OR :right IN(u.roles.rights))", int.class)
                .setParameter("username", username)
                .setParameter("right", right)
                .getFirstResult() == 0;
    }
}