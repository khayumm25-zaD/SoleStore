package com.solestore.exception;

import com.solestore.dto.response.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound(ResourceNotFoundException exception) { return response(HttpStatus.NOT_FOUND, exception.getMessage(), Map.of()); }
    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> badRequest(RuntimeException exception) { return response(HttpStatus.BAD_REQUEST, exception.getMessage(), Map.of()); }
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> conflict(DuplicateResourceException exception) { return response(HttpStatus.CONFLICT, exception.getMessage(), Map.of()); }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validation(MethodArgumentNotValidException exception) { Map<String, String> errors = new LinkedHashMap<>(); exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage())); return response(HttpStatus.BAD_REQUEST, "Validation failed", errors); }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> dataConflict(DataIntegrityViolationException exception) { return response(HttpStatus.CONFLICT, "The request conflicts with existing data", Map.of()); }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> unexpected(Exception exception) { return response(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", Map.of()); }
    private ResponseEntity<ErrorResponse> response(HttpStatus status, String message, Map<String, String> errors) { return ResponseEntity.status(status).body(new ErrorResponse(LocalDateTime.now(), status.value(), message, errors)); }
}