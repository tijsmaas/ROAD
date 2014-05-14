/*
 * Copyright by AIDaS.
 */

package aidas.userservice.converters;

import aidas.userservice.dto.UserDto;
import aidas.userservice.entities.UserEntity;

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
        return new UserDto(userEntity.getId(), userEntity.getUsername());
    }
}
