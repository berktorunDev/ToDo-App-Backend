package com.project.to_do_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.to_do_backend.dto.StatusDTO;
import com.project.to_do_backend.service.StatusService;
import com.project.to_do_backend.util.responseHandler.ResponseHandler;

@RestController
@RequestMapping("/status")
public class StatusController {
    private final StatusService statusService;

    // Constructor to inject the StatusService
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    /**
     * Get a list of all status types.
     *
     * @return ResponseEntity with a list of status types as StatusDTOs if
     *         successful,
     *         or an error message and status code if unsuccessful.
     */
    @GetMapping("/getAll")
    public ResponseEntity<Object> getStatus() {
        // Call the StatusService to retrieve a list of StatusDTOs
        List<StatusDTO> resultList = statusService.getStatus();

        // Check if the resultList is not null
        if (resultList != null) {
            // Return a success response with HTTP status OK and a success message
            return ResponseHandler.successResponse(HttpStatus.OK, "Status types fetched successfully!", resultList);
        } else {
            // Return an error response with HTTP status INTERNAL_SERVER_ERROR and an error
            // message
            return ResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Status types fetch is failed!");
        }
    }
}