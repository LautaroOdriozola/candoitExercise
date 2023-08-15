package com.group.candoit.service.serviceImpl;

import com.group.candoit.config.JwtTokenUtil;
import com.group.candoit.dto.AuthRequestDto;
import com.group.candoit.dto.AuthResponseDto;
import com.group.candoit.dto.UserDto;
import com.group.candoit.entity.UserEntity;
import com.group.candoit.repository.UserRepository;
import com.group.candoit.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtTokenUtil jwtUtil;
    @Autowired
    private AuthenticationManager authManager;

    @Override
    public UserEntity saveUser(UserDto userDto) {
        Optional<UserEntity> userEntityOptional = findByEmail(userDto.getEmail());
        UserEntity userEntity;
        if(userEntityOptional.isPresent()){
            userEntity = userEntityOptional.get();
            userEntity.setName(userDto.getName());
            userEntity.setSurname(userDto.getSurname());
        } else{
            userEntity = modelMapper.map(userDto, UserEntity.class);
        }

        String accessToken = jwtUtil.generateAccessToken(userEntity);
        userEntity.setToken(accessToken);
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public AuthResponseDto authentication(AuthRequestDto authRequestDto) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword()));
        if(!authentication.isAuthenticated()){
            throw new BadCredentialsException("Bad credentials. ");
        }
        Optional<UserEntity> userOptional = userRepository.findByEmail(authRequestDto.getEmail());
        UserEntity user = null;
        if (userOptional.isPresent()){
            user = userOptional.get();
        } else{
            throw new BadCredentialsException("There is no user with that email");
        }
        String accessToken = jwtUtil.generateAccessToken(user);
        user.setToken(accessToken);
        userRepository.save(user);
        return new AuthResponseDto(user.getEmail(), accessToken);
    }

    @Override
    public void deleteUser(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }

}
