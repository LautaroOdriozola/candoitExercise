package com.group.candoit.service.serviceImpl;

import com.group.candoit.entity.UserEntity;
import com.group.candoit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(username);

        if(userEntityOptional.isEmpty()){
            throw new UsernameNotFoundException("No se logro encontrar el usuario especificado.");
        }

        return userEntityOptional.get();
    }
}
