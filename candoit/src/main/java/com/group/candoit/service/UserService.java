package com.group.candoit.service;

import com.group.candoit.dto.AuthRequestDto;
import com.group.candoit.dto.AuthResponseDto;
import com.group.candoit.dto.UserDto;
import com.group.candoit.entity.UserEntity;

import java.util.Optional;

public interface UserService {

    UserEntity saveUser(UserDto userDto);
    Optional<UserEntity> findByEmail(String email);
    AuthResponseDto authentication(AuthRequestDto authRequestDto);
    void deleteUser(UserEntity userEntity);
}
