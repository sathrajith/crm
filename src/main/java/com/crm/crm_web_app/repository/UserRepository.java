package com.crm.crm_web_app.repository;

import com.crm.crm_web_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // This method should return Optional<User>
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
