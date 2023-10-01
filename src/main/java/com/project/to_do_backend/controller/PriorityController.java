package com.project.to_do_backend.controller;

import java.util.List;

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
        // Call the PriorityService to retrieve a list of PriorityDTOs
        List<PriorityDTO> resultList = priorityService.getPriorities();

        // Check if the resultList is not null
        if (resultList != null) {
            // Return a success response with HTTP status OK and a success message
            return ResponseHandler.successResponse(HttpStatus.OK, "Priority types fetched successfully!", resultList);
        } else {
            // Return an error response with HTTP status INTERNAL_SERVER_ERROR and an error
            // message
            return ResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Priority types fetch is failed!");
        }
    }
}
