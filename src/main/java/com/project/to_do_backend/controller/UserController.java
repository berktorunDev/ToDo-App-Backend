package com.project.to_do_backend.controller;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.to_do_backend.dto.UserDTO;
import com.project.to_do_backend.model.User;
import com.project.to_do_backend.service.UserService;
import com.project.to_do_backend.util.responseHandler.ResponseHandler;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Create a new user.
     *
     * @param user The user object to create.
     * @return ResponseEntity with a success message and the created user as UserDTO
     *         if successful, or an error message and status code if unsuccessful.
     */
    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        logger.info("Creating a new user...");
        UserDTO createdUser = userService.createUser(user);
        if (createdUser != null) {
            logger.info("User created successfully!");
            return ResponseHandler.successResponse(HttpStatus.CREATED, "User created successfully!", createdUser);
        } else {
            logger.error("User creation failed because createdUser is null!");
            return ResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "User creation failed!");
        }
    }

    /**
     * Get a user by their unique ID.
     *
     * @param id The ID of the user to retrieve.
     * @return ResponseEntity with the user as UserDTO and a success message if
     *         found, or an error message and status code if not found.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable UUID id) {
        logger.info("Fetching user by ID: {}", id);
        UserDTO user = userService.getUserById(id);
        if (user != null) {
            logger.info("User fetched successfully!");
            return ResponseHandler.successResponse(HttpStatus.OK, "User fetched successfully!", user);
        } else {
            logger.info("User not found because user is null!");
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "User not found!");
        }
    }

    /**
     * Get a list of all users in the system.
     *
     * @return ResponseEntity with a list of users as UserDTOs and a success
     *         message if found, or an error message and status code if no users are
     *         found.
     */
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllUsers() {
        logger.info("Fetching all users...");
        List<UserDTO> userList = userService.getAllUsers();
        if (!userList.isEmpty()) {
            logger.info("Users fetched successfully!");
            return ResponseHandler.successResponse(HttpStatus.OK, "Users fetched successfully!", userList);
        } else {
            logger.info("No users found because userList is empty!");
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "No users found!");
        }
    }

    /**
     * Delete a user by their unique ID.
     *
     * @param id The ID of the user to delete.
     * @return ResponseEntity with a success status code if the user is deleted
     *         successfully, or an error status code if the user is not found.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
        logger.info("Deleting user with ID: {}", id);
        UserDTO user = userService.getUserById(id);
        if (user != null) {
            userService.deleteUser(id);
            logger.info("User deleted successfully!");
            return ResponseHandler.successResponseWithoutData(HttpStatus.OK, "User deleted successfully!");
        }
        logger.info("User not found because user is null!");
        return ResponseHandler.successResponseWithoutData(HttpStatus.NOT_FOUND, "Don't have any user with ID: " + id);
    }

    /**
     * Update an existing user's information.
     *
     * @param id          The ID of the user to update.
     * @param updatedUser The updated user object.
     * @return ResponseEntity with the updated user as UserDTO and a success message
     *         if the update is successful, or an error message and status code if
     *         the user is not found or the update fails.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
        logger.info("Updating user with ID: {}", id);
        UserDTO updatedUserDTO = userService.updateUser(id, updatedUser);
        if (updatedUserDTO != null) {
            logger.info("User updated successfully!");
            return ResponseHandler.successResponse(HttpStatus.OK, "User updated successfully!", updatedUserDTO);
        } else {
            logger.info("User not found or update failed because updatedUserDTO is null!");
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "User not found or update failed!");
        }
    }
}
