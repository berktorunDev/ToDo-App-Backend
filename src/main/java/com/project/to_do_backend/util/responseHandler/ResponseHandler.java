package com.project.to_do_backend.util.responseHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    public static ResponseEntity<Object> successResponse(HttpStatus status, String infoMessage, Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("status", status.value());
        result.put("data", data);
        result.put("infoMessage", infoMessage);

        return new ResponseEntity<>(result, status);
    }

    public static ResponseEntity<Object> errorResponse(HttpStatus status, String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        result.put("status", status.value());
        result.put("message", message);

        return new ResponseEntity<>(result, status);
    }
}