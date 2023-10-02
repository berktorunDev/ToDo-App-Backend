package com.project.to_do_backend.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(StatusController.class);

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
        logger.info("Fetching all status types...");
        List<StatusDTO> resultList = statusService.getStatus();
        if (resultList != null) {
            logger.info("Status types fetched successfully!");
            return ResponseHandler.successResponse(HttpStatus.OK, "Status types fetched successfully!", resultList);
        } else {
            logger.error("Status types fetch failed because resultList is null!");
            return ResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Status types fetch failed!");
        }
    }
}
