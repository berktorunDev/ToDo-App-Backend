package com.project.to_do_backend.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.to_do_backend.dto.StatusDTO;
import com.project.to_do_backend.model.Status;
import com.project.to_do_backend.model.StatusType;
import com.project.to_do_backend.repository.StatusRepository;
import com.project.to_do_backend.util.mapper.MapperUtil;

@Service
public class StatusService {
    private final StatusRepository statusRepository;
    private final MapperUtil mapperUtil;
    private final RedisService redisService;

    public StatusService(StatusRepository statusRepository, MapperUtil mapperUtil, RedisService redisService) {
        this.statusRepository = statusRepository;
        this.mapperUtil = mapperUtil;
        this.redisService = redisService;
    }

    /**
     * Retrieves a list of statuses.
     *
     * @return A list of statuses as StatusDTOs.
     */
    public List<StatusDTO> getStatus() {
        // Attempt to retrieve status values from Redis
        Set<String> statusValues = redisService.getCachedEnumValues("Status");

        if (statusValues != null && !statusValues.isEmpty()) {
            // Convert the Redis values to a list of StatusDTOs and return it
            List<StatusDTO> statusDTOs = statusValues.stream()
                    .map(statusValue -> new StatusDTO(UUID.randomUUID(), StatusType.valueOf(statusValue)))
                    .collect(Collectors.toList());
            return statusDTOs;
        }

        // If not found in Redis, fetch from the database
        List<Status> statusList = statusRepository.findAll();

        // Cache the fetched values in Redis for future use
        Set<String> statusNames = statusList.stream()
                .map(status -> status.getStatusType().name())
                .collect(Collectors.toSet());
        redisService.cacheEnumValues("Status", statusNames);

        // Convert the list of statuses to a list of StatusDTOs and return it
        return statusList.stream()
                .map(status -> mapperUtil.convertToDTO(status, StatusDTO.class))
                .collect(Collectors.toList());
    }
}
