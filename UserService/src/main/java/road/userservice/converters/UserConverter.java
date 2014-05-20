/*
 * Copyright by AIDaS.
 */

package road.userservice.converters;

import road.userservice.dto.UserDto;
import road.userservice.entities.UserEntity;

/**
 * This class represents the converter which can be used to convert the {@link UserEntity} class.
 *
 * @author Geert
 */
public class UserConverter {

    /**
     * Convert the {@link UserEntity} to a {@link UserDto} class.
     * @param userEntity the user entity to convert.
     * @return the converted user entity.
     */
    public static UserDto toUserDto(UserEntity userEntity) {
        UserDto user = new UserDto(userEntity.getId(), userEntity.getUsername());
        user.setName(userEntity.getName());
        user.setStreet(userEntity.getStreet());
        user.setHouseNumber(userEntity.getHouseNumber());
        user.setPostalCode(userEntity.getPostalCode());
        user.setCity(userEntity.getCity());

        return user;
    }
}
