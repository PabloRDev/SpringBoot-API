package com.pablordev.springbootAPI.service;

import com.pablordev.springbootAPI.entity.User;
import com.pablordev.springbootAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        if (!userRepository.existsById(id)) throw new IllegalStateException("User with id " + id + " does not exist");

        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        if (!userRepository.existsByEmail(email)) throw new IllegalStateException("User with email " + email + " does not exist");

        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) throw new IllegalStateException("User with id " + id + " does not exist");

        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
}
