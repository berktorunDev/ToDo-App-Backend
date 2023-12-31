package com.project.to_do_backend.util.responseHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    /**
     * Used to create a successful response.
     *
     * @param status      HTTP response status (e.g., HttpStatus.OK)
     * @param infoMessage Informational message to be sent along with the successful
     *                    response
     * @param data        Response data
     * @return A ResponseEntity representing a successful response
     */
    public static ResponseEntity<Object> successResponse(HttpStatus status, String infoMessage, Object data) {
        // Create a map containing the fundamental properties of the response
        Map<String, Object> result = new HashMap<>();
        result.put("success", Boolean.TRUE);
        result.put("status", status.value());
        result.put("data", data);
        result.put("infoMessage", infoMessage);

        // Create and return the ResponseEntity
        return new ResponseEntity<>(result, status);
    }

    /**
     * Used to create a successful response without data.
     *
     * @param status      HTTP response status (e.g., HttpStatus.OK)
     * @param infoMessage Informational message to be sent along with the successful
     *                    response
     * @return A ResponseEntity representing a successful response
     */
    public static ResponseEntity<Object> successResponseWithoutData(HttpStatus status, String infoMessage) {
        // Create a map containing the fundamental properties of the response
        Map<String, Object> result = new HashMap<>();
        result.put("success", Boolean.TRUE);
        result.put("status", status.value());
        result.put("infoMessage", infoMessage);

        // Create and return the ResponseEntity
        return new ResponseEntity<>(result, status);
    }

    /**
     * Used to create error responses.
     *
     * @param status  HTTP response status (e.g., HttpStatus.BAD_REQUEST)
     * @param message Error message
     * @return A ResponseEntity representing an error response
     */
    public static ResponseEntity<Object> errorResponse(HttpStatus status, String message) {
        // Create a map containing the properties of the error response
        Map<String, Object> result = new HashMap<>();
        result.put("success", Boolean.FALSE);
        result.put("status", status.value());
        result.put("message", message);

        // Create and return the ResponseEntity
        return new ResponseEntity<>(result, status);
    }
}