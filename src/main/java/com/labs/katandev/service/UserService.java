package com.labs.katandev.service;

import com.labs.katandev.domain.entity.User;
import java.util.Optional;


public interface UserService {

    public Optional<User> getByUsername(String username);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);

    public void save(User user);

}
