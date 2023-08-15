package com.group.candoit.config;

import com.group.candoit.entity.Role;
import com.group.candoit.entity.UserEntity;
import com.group.candoit.repository.RolesRepository;
import com.group.candoit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;
    @Autowired
    private UserRepository usuarioRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtUtil;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if(alreadySetup)
            return;

        Role roleAdmin = createRole("ADMIN");
        Role roleRead = createRole("LECTURA");

        UserEntity userAdmin = new UserEntity();
        Optional<UserEntity> userAdminOptional = usuarioRepository.findByEmail("admin@test.com");
        if(userAdminOptional.isEmpty()){
            userAdmin.setName("Lautaro");
            userAdmin.setSurname("Gomez");
            userAdmin.setPassword(passwordEncoder.encode("admin"));
            userAdmin.setEmail("admin@test.com");
            userAdmin.setUserRoles(Stream.of(roleAdmin, roleRead).collect(Collectors.toSet()));
            String accessTokenAdmin = jwtUtil.generateAccessToken(userAdmin);
            userAdmin.setToken(accessTokenAdmin);
        } else{
            userAdmin = userAdminOptional.get();
        }

        UserEntity userRead = new UserEntity();
        Optional<UserEntity> userReadOptional = usuarioRepository.findByEmail("lector@test.com");
        if(userReadOptional.isEmpty()){
            userRead.setName("Juan");
            userRead.setSurname("Perez");
            userRead.setPassword(passwordEncoder.encode("lectura"));
            userRead.setEmail("lector@test.com");
            userRead.setUserRoles(Stream.of(roleRead).collect(Collectors.toSet()));
            String accessTokenLector = jwtUtil.generateAccessToken(userRead);
            userRead.setToken(accessTokenLector);
        } {
            userRead = userReadOptional.get();
        }

        usuarioRepository.saveAll(Arrays.asList(userAdmin, userRead));
        alreadySetup = true;
    }

    @Transactional
    public Role createRole(String name) {
        Optional<Role> roleOptional = rolesRepository.findByName(name);

        if(roleOptional.isEmpty()){
            Role role = new Role();
            role.setName(name);
            return role;
        }
        return roleOptional.get();
    }
}
