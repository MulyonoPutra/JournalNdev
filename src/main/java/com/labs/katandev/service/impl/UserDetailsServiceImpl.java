package com.labs.katandev.service.impl;

import com.labs.katandev.domain.entity.User;
import com.labs.katandev.domain.entity.UserPrincipal;
import com.labs.katandev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username)  {
        User user = userService.getByUsername(username).get();
        return UserPrincipal.build(user);
    }
}
