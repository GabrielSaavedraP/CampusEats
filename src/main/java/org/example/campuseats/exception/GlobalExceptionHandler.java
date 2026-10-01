package org.example.campuseats.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex){
        return build(HttpStatus.CONFLICT, ex.getMessage());
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex){
        return build(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }
    @ExceptionHandler(StoreNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStoreNotFoundException(StoreNotFoundException ex){
        return build(HttpStatus.NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex){
        return build(HttpStatus.NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientStockException(InsufficientStockException ex){
        return build(HttpStatus.CONFLICT, ex.getMessage());
    }
    @ExceptionHandler(ProductNotAvailableException.class)
    public ResponseEntity<ErrorResponse> handleProductNotAvailableException(ProductNotAvailableException ex){
        return build(HttpStatus.CONFLICT, ex.getMessage());
    }
    @ExceptionHandler(ForbiddenStoreActionException.class)
    public ResponseEntity<ErrorResponse> handleForbiddenStoreActionException(ForbiddenStoreActionException ex){
        return build(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult().getFieldErrors().stream().findFirst()
                .map(e -> e.getField() + ": " + e.getDefaultMessage()).orElse("Validation Error");
        return build(HttpStatus.BAD_REQUEST, message);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleGeneric(RuntimeException ex){
        return build(HttpStatus.BAD_REQUEST, ex.getMessage());}
    private ResponseEntity<ErrorResponse> build(HttpStatus status, String message){
        return ResponseEntity.status(status).body(new ErrorResponse(LocalDateTime.now(),
                status.value(),status.getReasonPhrase(), message));
    }
}

