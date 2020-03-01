package com.remote.exec.central.controller;


import com.remote.exec.central.models.entities.User;
import com.remote.exec.central.models.payload.JwtAuthenticationResponse;
import com.remote.exec.central.models.payload.LoginRequest;
import com.remote.exec.central.named.Endpoints;
import com.remote.exec.central.security.JwtTokenProvider;
import com.remote.exec.central.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping(Endpoints.Users.Id)
    public User getUser(@PathVariable @NotBlank String id) {
        return userService.getUserById(id);
    }

//    @GetMapping(Endpoints.Users.Base)
//    public User getCurrentUser(@Use @NotBlank String id) {
//        return userService.getUserById(id);
//    }

    @PostMapping(Endpoints.Users.SignUp)
    public User registerUser(@RequestBody @Valid @NotNull User user) {
        return userService.addUser(user);
    }

    @PostMapping(Endpoints.Users.Login)
    public JwtAuthenticationResponse authenticateStudent(@RequestBody @Valid @NotNull LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        return new JwtAuthenticationResponse(jwt);
    }
}
