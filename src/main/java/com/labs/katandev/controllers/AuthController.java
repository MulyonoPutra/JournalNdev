package com.labs.katandev.controllers;

import java.util.Set;
import java.util.HashSet;
import javax.validation.Valid;

import com.labs.katandev.domain.dto.JWTResponse;
import com.labs.katandev.domain.dto.Login;
import com.labs.katandev.domain.dto.Messages;
import com.labs.katandev.domain.dto.Register;
import com.labs.katandev.domain.entity.Role;
import com.labs.katandev.domain.entity.User;
import com.labs.katandev.domain.enums.RoleName;
import com.labs.katandev.security.JWTProvider;
import com.labs.katandev.service.RoleService;
import com.labs.katandev.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static com.labs.katandev.constants.SecurityConstants.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    JWTProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@Valid @RequestBody Register register, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Messages(INVALID), HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByUsername(register.getUsername())) {
            return new ResponseEntity<>(new Messages(USERNAME_EXIST), HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(register.getEmail())) {
            return new ResponseEntity<>(new Messages(EMAIL_EXIST), HttpStatus.BAD_REQUEST);
        }

        User user = new User(register.getName(), register.getUsername(), register.getEmail(),
                passwordEncoder.encode(register.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());

        if (register.getRoles().contains("admin")) {
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
        }

        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new Messages(CREATED), HttpStatus.CREATED);
    }

    /**
     * Login Controller with Bearer Token.
     *
     * @return JWT Response
     */
    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@Valid @RequestBody Login login, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Messages("Invalid!"), HttpStatus.BAD_REQUEST);
        }

        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtProvider.generateToken(auth);
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        JWTResponse jwtResponse = new JWTResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }
}
