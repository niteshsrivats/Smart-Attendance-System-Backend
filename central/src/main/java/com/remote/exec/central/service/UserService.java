package com.remote.exec.central.service;

import com.remote.exec.central.exceptions.BadRequestException;
import com.remote.exec.central.exceptions.DuplicateEntityException;
import com.remote.exec.central.exceptions.EntityNotFoundException;
import com.remote.exec.central.models.entities.User;
import com.remote.exec.central.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User getUserById(String id) {
        if (id == null) {
            throw new BadRequestException("Student id cannot be null.");
        }
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class, id));
    }


    @Transactional
    public User addUser(User user) {
        user.setId(RandomStringUtils.random(5, true, true));
        if (userRepository.existsById(user.getId())) {
            throw new DuplicateEntityException(User.class, user.getId());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
