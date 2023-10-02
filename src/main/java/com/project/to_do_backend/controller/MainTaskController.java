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

import com.project.to_do_backend.dto.MainTaskDTO;
import com.project.to_do_backend.model.MainTask;
import com.project.to_do_backend.service.MainTaskService;
import com.project.to_do_backend.util.responseHandler.ResponseHandler;

@RestController
@RequestMapping("/maintask")
public class MainTaskController {

    private static final Logger logger = LogManager.getLogger(MainTaskController.class);

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
        logger.info("Creating a new main task...");
        MainTaskDTO createdMainTask = mainTaskService.createMainTask(mainTask);

        if (createdMainTask != null) {
            logger.info("Main task created successfully!");
            return ResponseHandler.successResponse(HttpStatus.CREATED, "Main task created successfully!",
                    createdMainTask);
        } else {
            logger.error("Main task creation failed because createdMainTask is null!");
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
        logger.info("Fetching main task by ID: {}", id);
        MainTaskDTO mainTask = mainTaskService.getMainTaskById(id);

        if (mainTask != null) {
            logger.info("Main task fetched successfully!");
            return ResponseHandler.successResponse(HttpStatus.OK, "Main task fetched successfully!", mainTask);
        } else {
            logger.error("Main task not found because mainTask is null!");
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
        logger.info("Fetching all main tasks...");
        List<MainTaskDTO> mainTasks = mainTaskService.getAllMainTasks();

        if (mainTasks != null) {
            logger.info("Main tasks fetched successfully!");
            return ResponseHandler.successResponse(HttpStatus.OK, "Main tasks fetched successfully!", mainTasks);
        } else {
            logger.error("Main tasks fetch failed because mainTasks is null!");
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
        logger.info("Updating main task with ID: {}", id);
        MainTaskDTO updatedMainTask = mainTaskService.updateMainTask(id, updated);

        if (updatedMainTask != null) {
            logger.info("Main task updated successfully!");
            return ResponseHandler.successResponse(HttpStatus.OK, "Main task updated successfully!", updatedMainTask);
        } else {
            logger.error("Main task not found or update failed because updatedMainTask is null!");
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
        logger.info("Deleting main task with ID: {}", id);
        MainTaskDTO mainTask = mainTaskService.getMainTaskById(id);
        if (mainTask != null) {
            mainTaskService.deleteMainTask(id);
            logger.info("Main task deleted successfully!");
            return ResponseHandler.successResponseWithoutData(HttpStatus.OK, "Main task deleted successfully!");
        }
        logger.error("Main task not found because mainTask is null!");
        return ResponseHandler.successResponseWithoutData(HttpStatus.NOT_FOUND, "Don't have any maintasks!");
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
        logger.info("Deleting all main tasks...");
        Boolean anyMainTask = mainTaskService.anyMainTask();
        if (anyMainTask == Boolean.TRUE) {
            mainTaskService.deleteAllMainTasks();
            logger.info("All maintasks deleted successfully!");
            return ResponseHandler.successResponseWithoutData(HttpStatus.OK, "All maintasks deleted successfully!");
        }
        logger.error("Don't have any maintasks!");
        return ResponseHandler.successResponseWithoutData(HttpStatus.NO_CONTENT, "Don't have any maintasks!");
    }
}
