package com.project.to_do_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.to_do_backend.dto.SubTaskDTO;
import com.project.to_do_backend.model.SubTask;
import com.project.to_do_backend.repository.SubTaskRepository;
import com.project.to_do_backend.util.mapper.MapperUtil;

@Service
public class SubTaskService {

    private final SubTaskRepository subTaskRepository;
    private final MapperUtil mapperUtil;

    public SubTaskService(SubTaskRepository subTaskRepository, MapperUtil mapperUtil) {
        this.subTaskRepository = subTaskRepository;
        this.mapperUtil = mapperUtil;
    }

    /**
     * Creates a new subtask.
     *
     * @param subTask The subTask object to create.
     * @return The created subTask as a SubTaskDTO.
     */
    public SubTaskDTO createSubTask(SubTask subTask) {
        // Save the subtask to the database
        SubTask createdSubTask = subTaskRepository.save(subTask);

        // Convert the created user to a SubTaskDTO and return it
        return mapperUtil.convertToDTO(createdSubTask, SubTaskDTO.class);
    }

    /**
     * Gets a subtask by their unique ID.
     *
     * @param id The ID of the subtask to retrieve.
     * @return The subtask as a SubTaskDTO if found, or null if not found.
     */
    public SubTaskDTO getSubTaskById(UUID id) {
        // Find the subtask in the database by ID
        Optional<SubTask> subTaskOptional = subTaskRepository.findById(id);

        // Convert the subtask to a SubTaskDTO and return it, or return null if not
        // found
        return mapperUtil.convertToDTO(subTaskOptional, SubTaskDTO.class);
    }

    /**
     * Gets a list of all subtasks in the system.
     *
     * @return A list of subtasks as SubTaskDTOs.
     */
    public List<SubTaskDTO> getAllSubTasks() {
        // Retrieve all users from the database
        List<SubTask> subTaskList = subTaskRepository.findAll();

        // Convert the list of users to a list of SubTaskDTOs and return it
        return subTaskList.stream()
                .map(subtask -> mapperUtil.convertToDTO(subtask, SubTaskDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Updates an existing subTask's information.
     *
     * @param id      The ID of the subTask to update.
     * @param updated The updated subTask object.
     * @return The updated subTask as a SubTaskDTO if found, or null if not found.
     */
    public SubTaskDTO updateSubTask(UUID id, SubTask updated) {
        // First, check if the subTask with the given ID exists in the database
        Optional<SubTask> existingSubTaskOptional = subTaskRepository.findById(id);

        if (existingSubTaskOptional.isEmpty()) {
            // If the subTask does not exist, return null or throw an exception to indicate
            // that the subTask was not found
            return null; // You can also throw a custom exception here
        }

        // Get the existing subTask from the optional
        SubTask existingSubTask = existingSubTaskOptional.get();

        // Update the fields of the existing subTask with the values from the updated
        // subTask
        existingSubTask.setDescription(updated.getDescription());
        existingSubTask.setPriority(updated.getPriority());
        existingSubTask.setStatus(updated.getStatus());
        existingSubTask.setUser(updated.getUser());

        // Save the updated subTask to the database
        SubTask savedSubTask = subTaskRepository.save(existingSubTask);

        // Convert the saved subTask to SubTaskDTO and return it
        return mapperUtil.convertToDTO(savedSubTask, SubTaskDTO.class);
    }

    /**
     * Deletes a subtask by their unique ID.
     *
     * @param id The ID of the subtask to delete.
     */
    public void deleteSubTask(UUID id) {
        // Delete the subtask from the database by ID
        subTaskRepository.deleteById(id);
    }

    /**
     * Deletes all a subtasks
     */
    public void deleteAllSubTasks() {
        // Delete all subtasks from the database
        subTaskRepository.deleteAll();
    }

    /**
     * Checks if there are any subtasks in the database.
     *
     * @return true if there are subtasks, false otherwise.
     */
    public boolean anySubTask() {
        long subTaskCount = subTaskRepository.count();
        return subTaskCount > 0;
    }
}
