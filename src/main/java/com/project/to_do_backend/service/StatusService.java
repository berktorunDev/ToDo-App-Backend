package com.project.to_do_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.to_do_backend.dto.StatusDTO;
import com.project.to_do_backend.model.Status;
import com.project.to_do_backend.repository.StatusRepository;
import com.project.to_do_backend.util.mapper.MapperUtil;

@Service
public class StatusService {
    private final StatusRepository statusRepository;
    private final MapperUtil mapperUtil;

    // Constructor for StatusService
    public StatusService(StatusRepository statusRepository, MapperUtil mapperUtil) {
        this.statusRepository = statusRepository;
        this.mapperUtil = mapperUtil;
    }

    /**
     * Retrieves a list of statuses.
     *
     * @return A list of statuses as StatusDTOs.
     */
    public List<StatusDTO> getStatus() {
        // Retrieve all statuses from the database
        List<Status> statusList = statusRepository.findAll();

        // Convert the list of statuses to a list of StatusDTOs and return it
        return statusList.stream()
                .map(status -> mapperUtil.convertToDTO(status, StatusDTO.class))
                .collect(Collectors.toList());
    }
}
