package com.project.to_do_backend.controller;

import java.util.List;
import java.util.UUID;

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
        UserDTO createdUser = userService.createUser(user);
        if (createdUser != null) {
            return ResponseHandler.successResponse(HttpStatus.CREATED, "User created successfully!",
                    createdUser);
        } else {
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
        UserDTO user = userService.getUserById(id);
        if (user != null) {
            return ResponseHandler.successResponse(HttpStatus.OK, "User fetched successfully!", user);
        } else {
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
        List<UserDTO> userList = userService.getAllUsers();
        if (!userList.isEmpty()) {
            return ResponseHandler.successResponse(HttpStatus.OK, "Users fetched successfully!", userList);
        } else {
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
        UserDTO user = userService.getUserById(id);
        if (user != null) {
            userService.deleteUser(id);
            return ResponseHandler.successResponseWithoutData(HttpStatus.OK, "User deleted successfully!");
        }
        return ResponseHandler.successResponseWithoutData(HttpStatus.NOT_FOUND, "Don't have a any user!");
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
        UserDTO updatedUserDTO = userService.updateUser(id, updatedUser);
        if (updatedUserDTO != null) {
            return ResponseHandler.successResponse(HttpStatus.OK, "User updated successfully!", updatedUserDTO);
        } else {
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "User not found or update failed!");
        }
    }
}
