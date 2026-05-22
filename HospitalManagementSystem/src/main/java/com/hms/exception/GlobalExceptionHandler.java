package com.hms.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hms.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleResourceNotFound(ResourceNotFoundException ex) 
    {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HospitalNotFoundException.class) 
    public ResponseEntity<ApiResponse<String>> handleHospitalNotFound(HospitalNotFoundException ex) 
    {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<String>> handleDuplicateResource(DuplicateResourceException ex) {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleInvalidUpdate(InvalidOperationException ex) {
        ApiResponse<Map<String, String>> response = ApiResponse.<Map<String, String>>builder()
                .success(false)
                .message("Invalid operation")
                .data(ex.getErrors())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HospitalAlreadyActiveException.class)
    public ResponseEntity<ApiResponse<String>> handleAlreadyActive(HospitalAlreadyActiveException ex) { // ✅ Fixed name
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT); 
    }
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiResponse<String>> branchAlreadydeleted(ResourceNotFoundException res)
//    {
//    	 ApiResponse<String> response = ApiResponse.<String>builder()
//                 .success(false)
//                 .message(res.getMessage())
//                 .data(null)
//                 .build();
//         return new ResponseEntity<>(response, HttpStatus.CONFLICT); 
//    }
}