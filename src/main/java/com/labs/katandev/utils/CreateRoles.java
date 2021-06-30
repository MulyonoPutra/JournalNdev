package com.labs.katandev.utils;

import com.labs.katandev.domain.entity.Role;
import com.labs.katandev.domain.enums.RoleName;
import com.labs.katandev.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception {

        /**
         * Disable this comment if want to create new User Role
         *
         */

        //  Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
        //  Role roleUser = new Role(RoleName.ROLE_USER);
        //  roleService.save(roleAdmin);
        //  roleService.save(roleUser);

    }



}
