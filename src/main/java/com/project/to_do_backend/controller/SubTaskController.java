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

import com.project.to_do_backend.dto.SubTaskDTO;
import com.project.to_do_backend.model.SubTask;
import com.project.to_do_backend.service.SubTaskService;
import com.project.to_do_backend.util.responseHandler.ResponseHandler;

@RestController
@RequestMapping("/subtask")
public class SubTaskController {

    private static final Logger logger = LogManager.getLogger(SubTaskController.class);

    private final SubTaskService subTaskService;

    public SubTaskController(SubTaskService subTaskService) {
        this.subTaskService = subTaskService;
    }

    /**
     * Create a new subtask.
     *
     * @param subTask The subTask object to create.
     * @return ResponseEntity with the created subtask as a SubTaskDTO if
     *         successful,
     *         or an error message and status code if unsuccessful.
     */
    @PostMapping("/create")
    public ResponseEntity<Object> createSubTask(@RequestBody SubTask subTask) {
        logger.info("Creating a new subtask...");
        SubTaskDTO createdSubTask = subTaskService.createSubTask(subTask);
        if (createdSubTask != null) {
            logger.info("Subtask created successfully!");
            return ResponseHandler.successResponse(HttpStatus.CREATED, "Subtask created successfully!", createdSubTask);
        } else {
            logger.error("Subtask creation failed!");
            return ResponseHandler.errorResponse(HttpStatus.BAD_REQUEST, "Subtask creation failed!");
        }
    }

    /**
     * Get a subtask by its unique ID.
     *
     * @param id The ID of the subtask to retrieve.
     * @return ResponseEntity with the retrieved subtask as a SubTaskDTO if found,
     *         or an error message and status code if not found.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getSubTaskById(@PathVariable UUID id) {
        logger.info("Fetching subtask by ID: {}", id);
        SubTaskDTO subTask = subTaskService.getSubTaskById(id);
        if (subTask != null) {
            logger.info("Subtask fetched successfully!");
            return ResponseHandler.successResponse(HttpStatus.OK, "Subtask fetched successfully!", subTask);
        } else {
            logger.error("Subtask not found!");
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "Subtask not found!");
        }
    }

    /**
     * Get a list of all subtasks in the system.
     *
     * @return ResponseEntity with a list of subtasks as SubTaskDTOs if successful,
     *         or an error message and status code if unsuccessful.
     */
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllSubTasks() {
        logger.info("Fetching all subtasks...");
        List<SubTaskDTO> subTasks = subTaskService.getAllSubTasks();
        String infoMessage = subTasks.isEmpty() ? "Don't have any subtasks" : "Subtasks fetched successfully!";
        logger.info(infoMessage);
        return ResponseHandler.successResponse(HttpStatus.OK, infoMessage, subTasks);
    }

    /**
     * Update an existing subtask's information.
     *
     * @param id      The ID of the subtask to update.
     * @param updated The updated subtask object.
     * @return ResponseEntity with the updated subtask as a SubTaskDTO if
     *         successful,
     *         or an error message and status code if not found.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateSubTask(@PathVariable UUID id, @RequestBody SubTask updatedSubTask) {
        logger.info("Updating subtask with ID: {}", id);
        SubTaskDTO updatedSubTaskDTO = subTaskService.updateSubTask(id, updatedSubTask);
        if (updatedSubTaskDTO != null) {
            logger.info("Subtask updated successfully!");
            return ResponseHandler.successResponse(HttpStatus.OK, "Subtask updated successfully!", updatedSubTaskDTO);
        } else {
            logger.error("Subtask not found or update failed!");
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "Subtask not found or update failed!");
        }
    }

    /**
     * Delete a subtask by its unique ID.
     *
     * @param id The ID of the subtask to delete.
     * @return ResponseEntity with a success message and status code if successful,
     *         or an error message and status code if not found.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteSubTask(@PathVariable UUID id) {
        logger.info("Deleting subtask with ID: {}", id);
        SubTaskDTO subTask = subTaskService.getSubTaskById(id);
        if (subTask != null) {
            subTaskService.deleteSubTask(id);
            logger.info("Subtask deleted successfully!");
            return ResponseHandler.successResponseWithoutData(HttpStatus.OK, "Subtask deleted successfully!");
        }
        logger.error("Don't have any subtasks with ID: {}", id);
        return ResponseHandler.successResponseWithoutData(HttpStatus.NOT_FOUND,
                "Don't have any subtasks with ID: " + id);
    }

    /**
     * Delete all subtasks.
     *
     * @return ResponseEntity with a success message and status code if successful,
     *         or an error message and status code if unsuccessful.
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Object> deleteAllSubTasks() {
        logger.info("Deleting all subtasks...");
        Boolean anySubTask = subTaskService.anySubTask();
        if (anySubTask == Boolean.TRUE) {
            subTaskService.deleteAllSubTasks();
            logger.info("All subtasks deleted successfully!");
            return ResponseHandler.successResponseWithoutData(HttpStatus.OK, "All subtasks deleted successfully!");
        }
        logger.info("Don't have any subtasks to delete.");
        return ResponseHandler.successResponseWithoutData(HttpStatus.NO_CONTENT, "Don't have any subtasks to delete.");
    }
}
