/*
 * Copyright by AIDaS.
 */

package aidas.usersystem;

import aidas.usersystem.dto.Right;
import aidas.usersystem.entities.UserEntity;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * The class representing the implementation of the {@link IUserManager}.
 * 
 * @author Geert
 */
public class UserManager implements IUserManager {
    
    /**
     * The persistence context used to connect to the database.
     */
    @PersistenceContext(name="UserSystem")
    private EntityManager em;
    
    /**
     * The mapping containing the users which have successfully authenticated 
     * and are still logged in.
     * {@link String}: the unique identifier provided for the user after authenticating.
     * {@link UserEntity}: the user mapped to the unique identifier.
     */
    private final Map<String, UserEntity> loggedInUsers;
    
    /**
     * Create a new instance of the {@link UserManager} class.
     */
    public UserManager() {
        this.loggedInUsers = new ConcurrentHashMap<>();
    }
    
    @Override
    public void demandRight(String authId, Right right) throws SecurityException {
        if (!this.verifyRight(authId, right)) {
            throw new SecurityException("The user doesn't have to provided right.");
        }
    }

    @Override
    public String login(String username, String password) {
        UserEntity user = this.em.createQuery("SELECT u FROM USERS u WHERE u.username = :username", UserEntity.class)
                .setParameter("username", username)
                .getSingleResult();
        
        String uniqueId = null;
        if (user!= null && user.getPassword().equals(Security.processPassword(password, username, user.getSalt()))) {
            uniqueId = this.getUniqueUserId();
            
            this.loggedInUsers.put(uniqueId, user);
        }
        
        return uniqueId;
    }

    @Override
    public void Register(String username, String password) throws IllegalArgumentException {
        if (username == null || username.isEmpty() || username.length() > 28) {
            throw new IllegalArgumentException("The username is mandatory and can have a maximum length of 28 characters.");
        } else if (password == null || password.isEmpty() || password.length() < 8 || password.length() > 4000) {
            throw new IllegalArgumentException("The password is mandatory and must have a length between 8 - 4000 characters.");
        }
        
        byte[] salt = Security.generateSalt();
        String processedPassword = Security.processPassword(password, username, salt);
        
        em.persist(new UserEntity(username, processedPassword, salt));
    }

    @Override
    public boolean verifyRight(String authId, Right right) {
        UserEntity user = this.loggedInUsers.get(authId);
        
        return user == null || this.em.createQuery("SELECT COUNT(u) FROM USERS u WHERE u.username = :username AND (:right IN(u.rights) OR :right IN(u.roles.rights))", int.class)
                .setParameter("username", user.getUsername())
                .setParameter("right", right)
                .getFirstResult() == 0;
    }
    
    /**
     * Get a unique identifier for the authenticated {@link UserEntity} to return to server.
     * @return the unique identifier.
     */
    private String getUniqueUserId() {
        String uuid;
        
        do {
            uuid = UUID.randomUUID().toString();
        } while(this.loggedInUsers.containsKey(uuid));
        
        return uuid;
    }
}
