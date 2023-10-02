package com.project.to_do_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.to_do_backend.dto.MainTaskDTO;
import com.project.to_do_backend.model.MainTask;
import com.project.to_do_backend.repository.MainTaskRepository;
import com.project.to_do_backend.util.mapper.MapperUtil;

@Service
public class MainTaskService {

    private final MainTaskRepository mainTaskRepository;
    private final MapperUtil mapperUtil;

    public MainTaskService(MainTaskRepository mainTaskRepository, MapperUtil mapperUtil) {
        this.mainTaskRepository = mainTaskRepository;
        this.mapperUtil = mapperUtil;
    }

    /**
     * Creates a new main task.
     *
     * @param mainTask The main task object to create.
     * @return The created main task as a MainTaskDTO.
     */
    public MainTaskDTO createMainTask(MainTask mainTask) {
        // Save the main task to the database
        MainTask createdMainTask = mainTaskRepository.save(mainTask);

        // Convert the created main task to a MainTaskDTO and return it
        return mapperUtil.convertToDTO(createdMainTask, MainTaskDTO.class);
    }

    /**
     * Gets a main task by its unique ID.
     *
     * @param id The ID of the main task to retrieve.
     * @return The main task as a MainTaskDTO if found, or null if not found.
     */
    public MainTaskDTO getMainTaskById(UUID id) {
        // Find the main task in the database by ID
        Optional<MainTask> mainTaskOptional = mainTaskRepository.findById(id);

        // Convert the main task to a MainTaskDTO and return it, or return null if not
        // found
        return mapperUtil.convertToDTO(mainTaskOptional, MainTaskDTO.class);
    }

    /**
     * Gets a list of all main tasks in the system.
     *
     * @return A list of main tasks as MainTaskDTOs.
     */
    public List<MainTaskDTO> getAllMainTasks() {
        // Retrieve all main tasks from the database
        List<MainTask> mainTaskList = mainTaskRepository.findAll();

        // Convert the list of main tasks to a list of MainTaskDTOs and return it
        return mainTaskList.stream()
                .map(mainTask -> mapperUtil.convertToDTO(mainTask, MainTaskDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Updates an existing main task's information.
     *
     * @param id      The ID of the main task to update.
     * @param updated The updated main task object.
     * @return The updated main task as a MainTaskDTO if found, or null if not
     *         found.
     */
    public MainTaskDTO updateMainTask(UUID id, MainTask updated) {
        // First, check if the main task with the given ID exists in the database
        Optional<MainTask> existingMainTaskOptional = mainTaskRepository.findById(id);

        if (existingMainTaskOptional.isEmpty()) {
            // If the main task does not exist, return null or throw an exception to
            // indicate
            // that the main task was not found
            return null; // You can also throw a custom exception here
        }

        // Get the existing main task from the optional
        MainTask existingMainTask = existingMainTaskOptional.get();

        // Update the fields of the existing main task with the values from the updated
        // main task
        existingMainTask.setDescription(updated.getDescription());
        existingMainTask.setPriority(updated.getPriority());
        existingMainTask.setStatus(updated.getStatus());
        existingMainTask.setUser(updated.getUser());
        existingMainTask.setSubTasks(updated.getSubTasks());

        // Save the updated main task to the database
        MainTask savedMainTask = mainTaskRepository.save(existingMainTask);

        // Convert the saved main task to MainTaskDTO and return it
        return mapperUtil.convertToDTO(savedMainTask, MainTaskDTO.class);
    }

    /**
     * Deletes a main task by its unique ID.
     *
     * @param id The ID of the main task to delete.
     */
    public void deleteMainTask(UUID id) {
        // Delete the main task from the database by ID
        mainTaskRepository.deleteById(id);
    }

    /**
     * Deletes all main tasks.
     */
    public void deleteAllMainTasks() {
        // Delete all main tasks from the database
        mainTaskRepository.deleteAll();
    }

    /**
     * Checks if there are any maintask in the database.
     *
     * @return true if there are maintasks, false otherwise.
     */
    public boolean anyMainTask() {
        long mainTaskCount = mainTaskRepository.count();
        return mainTaskCount > 0;
    }
}
