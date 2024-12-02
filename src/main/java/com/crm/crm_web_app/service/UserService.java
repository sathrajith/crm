package com.crm.crm_web_app.service;

import com.crm.crm_web_app.entity.Role;
import com.crm.crm_web_app.entity.User;
import com.crm.crm_web_app.repository.RoleRepository;
import com.crm.crm_web_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;



    // Constructor injection
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User registerUser(String username, String password, String roleName) {
        // Find the role by name
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            throw new RuntimeException("Role not found");
        }

        // Create a Set of roles (even if it's just one role)
        User user = new User(username, password, Collections.singleton(role)); // Use Collections.singleton to create a Set with one role

        return userRepository.save(user);
    }
}
