package com.project.to_do_backend.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.to_do_backend.dto.PriorityDTO;
import com.project.to_do_backend.model.Priority;
import com.project.to_do_backend.model.PriorityType;
import com.project.to_do_backend.repository.PriorityRepository;
import com.project.to_do_backend.util.mapper.MapperUtil;
import com.project.to_do_backend.util.service.redis.RedisService;

@Service
public class PriorityService {
    private final PriorityRepository priorityRepository;
    private final MapperUtil mapperUtil;
    private final RedisService redisService;

    // Constructor for PriorityService
    public PriorityService(PriorityRepository priorityRepository, MapperUtil mapperUtil, RedisService redisService) {
        this.priorityRepository = priorityRepository;
        this.mapperUtil = mapperUtil;
        this.redisService = redisService;
    }

    /**
     * Retrieves a list of priorities.
     *
     * @return A list of priorities as PriorityDTOs.
     */
    public List<PriorityDTO> getPriorities() {
        Set<String> priorityValues = redisService.getCachedEnumValues("Priority");

        if (priorityValues != null && !priorityValues.isEmpty()) {
            List<PriorityDTO> priorityDTOs = priorityValues.stream()
                    .map(priorityValue -> new PriorityDTO(UUID.randomUUID(), PriorityType.valueOf(priorityValue)))
                    .collect(Collectors.toList());
            return priorityDTOs;
        }

        List<Priority> priorityList = priorityRepository.findAll();
        Set<String> priorityNames = priorityList.stream()
                .map(priority -> priority.getPriorityType().name())
                .collect(Collectors.toSet());

        redisService.cacheEnumValues("Priority", priorityNames);

        return priorityList.stream()
                .map(priority -> mapperUtil.convertToDTO(priority, PriorityDTO.class))
                .collect(Collectors.toList());
    }
}
