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

import com.project.to_do_backend.dto.MainTaskDTO;
import com.project.to_do_backend.model.MainTask;
import com.project.to_do_backend.service.MainTaskService;
import com.project.to_do_backend.util.responseHandler.ResponseHandler;

@RestController
@RequestMapping("/maintask")
public class MainTaskController {

    private final MainTaskService mainTaskService;

    public MainTaskController(MainTaskService mainTaskService) {
        this.mainTaskService = mainTaskService;
    }

    /**
     * Create a new main task.
     *
     * @param mainTask The main task object to create.
     * @return ResponseEntity with the created main task as a MainTaskDTO if
     *         successful,
     *         or an error message and status code if unsuccessful.
     */
    @PostMapping("/create")
    public ResponseEntity<Object> createMainTask(@RequestBody MainTask mainTask) {
        MainTaskDTO createdMainTask = mainTaskService.createMainTask(mainTask);

        if (createdMainTask != null) {
            return ResponseHandler.successResponse(HttpStatus.CREATED, "Main task created successfully!",
                    createdMainTask);
        } else {
            return ResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Main task creation failed!");
        }
    }

    /**
     * Get a main task by its unique ID.
     *
     * @param id The ID of the main task to retrieve.
     * @return ResponseEntity with the main task as a MainTaskDTO if found,
     *         or an error message and status code if not found.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getMainTaskById(@PathVariable UUID id) {
        MainTaskDTO mainTask = mainTaskService.getMainTaskById(id);

        if (mainTask != null) {
            return ResponseHandler.successResponse(HttpStatus.OK, "Main task fetched successfully!", mainTask);
        } else {
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "Main task not found!");
        }
    }

    /**
     * Get a list of all main tasks.
     *
     * @return ResponseEntity with a list of main tasks as MainTaskDTOs if
     *         successful,
     *         or an error message and status code if unsuccessful.
     */
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllMainTasks() {
        List<MainTaskDTO> mainTasks = mainTaskService.getAllMainTasks();

        if (mainTasks != null) {
            return ResponseHandler.successResponse(HttpStatus.OK, "Main tasks fetched successfully!", mainTasks);
        } else {
            return ResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Main tasks fetch failed!");
        }
    }

    /**
     * Update an existing main task.
     *
     * @param id      The ID of the main task to update.
     * @param updated The updated main task object.
     * @return ResponseEntity with the updated main task as a MainTaskDTO if
     *         successful,
     *         or an error message and status code if not found or update failed.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateMainTask(@PathVariable UUID id, @RequestBody MainTask updated) {
        MainTaskDTO updatedMainTask = mainTaskService.updateMainTask(id, updated);

        if (updatedMainTask != null) {
            return ResponseHandler.successResponse(HttpStatus.OK, "Main task updated successfully!", updatedMainTask);
        } else {
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "Main task not found or update failed!");
        }
    }

    /**
     * Delete a main task by its unique ID.
     *
     * @param id The ID of the main task to delete.
     * @return ResponseEntity with a success message and status code if deletion is
     *         successful,
     *         or an error message and status code if not found or deletion failed.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteMainTask(@PathVariable UUID id) {
        MainTaskDTO mainTask = mainTaskService.getMainTaskById(id);
        if (mainTask != null) {
            mainTaskService.deleteMainTask(id);
            return ResponseHandler.successResponseWithoutData(HttpStatus.OK, "Maintask deleted successfully!");
        }
        return ResponseHandler.successResponseWithoutData(HttpStatus.NOT_FOUND, "Don't have a any maintasks!");
    }

    /**
     * Delete all main tasks.
     *
     * @return ResponseEntity with a success message and status code if deletion is
     *         successful,
     *         or an error message and status code if deletion failed.
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Object> deleteAllMainTasks() {
        Boolean anyMainTask = mainTaskService.anyMainTask();
        if (anyMainTask == Boolean.TRUE) {
            mainTaskService.deleteAllMainTasks();
            return ResponseHandler.successResponseWithoutData(HttpStatus.OK,
                    "All maintasks deleted successfully!");
        }
        return ResponseHandler.successResponseWithoutData(HttpStatus.NO_CONTENT,
                "Don't have a any maintasks!");
    }
}
