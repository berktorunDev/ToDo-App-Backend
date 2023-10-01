package com.project.to_do_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.to_do_backend.dto.PriorityDTO;
import com.project.to_do_backend.model.Priority;
import com.project.to_do_backend.repository.PriorityRepository;
import com.project.to_do_backend.util.mapper.MapperUtil;

@Service
public class PriorityService {
    private final PriorityRepository priorityRepository;
    private final MapperUtil mapperUtil;

    // Constructor for PriorityService
    public PriorityService(PriorityRepository priorityRepository, MapperUtil mapperUtil) {
        this.priorityRepository = priorityRepository;
        this.mapperUtil = mapperUtil;
    }

    /**
     * Retrieves a list of priorities.
     *
     * @return A list of priorities as PriorityDTOs.
     */
    public List<PriorityDTO> getPriorities() {
        // Retrieve all priorities from the database
        List<Priority> priorityList = priorityRepository.findAll();

        // Convert the list of priorities to a list of PriorityDTOs and return it
        return priorityList.stream()
                .map(priority -> mapperUtil.convertToDTO(priority, PriorityDTO.class))
                .collect(Collectors.toList());
    }
}
