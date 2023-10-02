package com.project.to_do_backend.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.to_do_backend.dto.PriorityDTO;
import com.project.to_do_backend.service.PriorityService;
import com.project.to_do_backend.util.responseHandler.ResponseHandler;

@RestController
@RequestMapping("/priority")
public class PriorityController {
    private static final Logger logger = LogManager.getLogger(PriorityController.class);

    private final PriorityService priorityService;

    // Constructor to inject the PriorityService
    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    /**
     * Get a list of all priorities.
     *
     * @return ResponseEntity with a list of priorities as PriorityDTOs if
     *         successful,
     *         or an error message and status code if unsuccessful.
     */
    @GetMapping("/getAll")
    public ResponseEntity<Object> getPriorities() {
        logger.info("Fetching all priority types...");
        List<PriorityDTO> resultList = priorityService.getPriorities();
        if (resultList != null) {
            logger.info("Priority types fetched successfully!");
            return ResponseHandler.successResponse(HttpStatus.OK, "Priority types fetched successfully!", resultList);
        } else {
            logger.error("Priority types fetch failed because resultList is null!");
            return ResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Priority types fetch failed!");
        }
    }
}
