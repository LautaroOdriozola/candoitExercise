package com.group.candoit.controller;


import com.group.candoit.dto.AuthRequestDto;
import com.group.candoit.dto.UserDto;
import com.group.candoit.entity.UserEntity;
import com.group.candoit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
            (consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDto userDto) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDto));
    }

    @PutMapping
            (consumes = MediaType.APPLICATION_JSON_VALUE,
             produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDto));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        Optional<UserEntity> userEntityOptional = userService.findByEmail(email);
        if(userEntityOptional.isPresent()){
            userService.deleteUser(userEntityOptional.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequestDto request) {
        return ResponseEntity.ok().body(userService.authentication(request));
    }

}
