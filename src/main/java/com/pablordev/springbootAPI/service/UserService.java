package com.pablordev.springbootAPI.service;

import com.pablordev.springbootAPI.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Iterable<User> findAll();
    Page<User> findAll(Pageable pageable);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    void deleteById(Long id);
    User save(User user);

    Boolean existsByEmail(String email);
}
