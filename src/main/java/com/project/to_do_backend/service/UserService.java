package com.project.to_do_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.to_do_backend.dto.UserDTO;
import com.project.to_do_backend.model.User;
import com.project.to_do_backend.repository.UserRepository;
import com.project.to_do_backend.util.mapper.MapperUtil;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;
    private final PasswordEncoder passwordEncoder;

    // Constructor for UserService
    public UserService(UserRepository userRepository, MapperUtil mapperUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new user.
     *
     * @param user The user object to create.
     * @return The created user as a UserDTO.
     */
    public UserDTO createUser(User user) {
        // Encrypt the user's password before saving
        user.encryptPassword();

        // Save the user to the database
        User createdUser = userRepository.save(user);

        // Convert the created user to a UserDTO and return it
        return mapperUtil.convertToDTO(createdUser, UserDTO.class);
    }

    /**
     * Gets a user by their unique ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user as a UserDTO if found, or null if not found.
     */
    public UserDTO getUserById(UUID id) {
        // Find the user in the database by ID
        Optional<User> user = userRepository.findById(id);

        // Convert the user to a UserDTO and return it, or return null if not found
        return mapperUtil.convertToDTO(user, UserDTO.class);
    }

    /**
     * Gets a list of all users in the system.
     *
     * @return A list of users as UserDTOs.
     */
    public List<UserDTO> getAllUsers() {
        // Retrieve all users from the database
        List<User> userList = userRepository.findAll();

        // Convert the list of users to a list of UserDTOs and return it
        return userList.stream()
                .map(user -> mapperUtil.convertToDTO(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Deletes a user by their unique ID.
     *
     * @param id The ID of the user to delete.
     */
    public void deleteUser(UUID id) {
        // Delete the user from the database by ID
        userRepository.deleteById(id);
    }

    /**
     * Updates an existing user's information.
     *
     * @param id      The ID of the user to update.
     * @param updated The updated user object.
     * @return The updated user as a UserDTO if found, or null if not found.
     */
    public UserDTO updateUser(UUID id, User updated) {
        // First, check if the user with the given ID exists in the database
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isEmpty()) {
            // If the user does not exist, return null or throw an exception to indicate
            // that the user was not found
            return null; // You can also throw a custom exception here
        }

        // Get the existing user from the optional
        User existingUser = existingUserOptional.get();

        // Update the fields of the existing user with the values from the updated user
        existingUser.setUsername(updated.getUsername());
        existingUser.setEmail(updated.getEmail());

        // Check if the updated user has provided a new password
        if (updated.getPassword() != null && !updated.getPassword().isEmpty()) {
            // If a new password is provided, encrypt and update the password
            existingUser.setPassword(passwordEncoder.encode(updated.getPassword()));
        }

        // Save the updated user to the database
        User savedUser = userRepository.save(existingUser);

        // Convert the saved user to UserDTO and return it
        return mapperUtil.convertToDTO(savedUser, UserDTO.class);
    }

    /**
     * Verifies an existing user's information.
     *
     * @param id The ID of the user to update.
     * @return The verified user as a UserDTO if found, or null if not found.
     */
    public UserDTO verifyUser(UUID id) {
        // First, check if the user with the given ID exists in the database
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isEmpty()) {
            // If the user does not exist, return null or throw an exception to indicate
            // that the user was not found
            return null; // You can also throw a custom exception here
        }

        // Get the existing user from the optional
        User existingUser = existingUserOptional.get();

        // Update the fields of the existing user
        existingUser.setIsVerified(Boolean.TRUE);

        // Save the updated user to the database
        User savedUser = userRepository.save(existingUser);

        // Convert the saved user to UserDTO and return it
        return mapperUtil.convertToDTO(savedUser, UserDTO.class);
    }
}