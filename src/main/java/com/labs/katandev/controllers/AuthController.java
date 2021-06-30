package com.labs.katandev.controllers;

import com.labs.katandev.domain.dto.JwtResponse;
import com.labs.katandev.domain.dto.Login;
import com.labs.katandev.domain.dto.Messages;
import com.labs.katandev.domain.dto.Register;
import com.labs.katandev.domain.entity.Role;
import com.labs.katandev.domain.entity.User;
import com.labs.katandev.domain.enums.RoleName;
import com.labs.katandev.security.JwtProvider;
import com.labs.katandev.service.RoleService;
import com.labs.katandev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

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
    JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@Valid @RequestBody Register register, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Messages("Invalid!"), HttpStatus.BAD_REQUEST);
        if(userService.existsByUsername(register.getUsername()))
            return new ResponseEntity<>(new Messages("Username already exist!"), HttpStatus.BAD_REQUEST);
        if(userService.existsByEmail(register.getEmail()))
            return new ResponseEntity<>(new Messages("Email already exist!"), HttpStatus.BAD_REQUEST);
        User user =
                new User(register.getName(), register.getUsername(), register.getEmail(),
                        passwordEncoder.encode(register.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
        if(register.getRoles().contains("admin")){
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
        }

        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new Messages("Created!"), HttpStatus.CREATED);
    }


    /**
     * Login Controller with Bearer Token.
     *
     * @return JWT Response
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody Login login, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Messages("Invalid!"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtResponse jwtDto = new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
