package dev.qna.qna_session_service.exception;

import dev.qna.qna_session_service.dto.BaseResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<BaseResponseDTO<Object>> handleSessionNotFound(SessionNotFoundException ex){
        return new ResponseEntity<>(
                new BaseResponseDTO<>(false, ex.getMessage(), null),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponseDTO<Object>> handleGenericException(Exception ex){
        return new ResponseEntity<>(new BaseResponseDTO<>(false, ex.getMessage(), null),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
    @ExceptionHandler
    public ResponseEntity<BaseResponseDTO<Object>> handleResourceNotFoundException(
            ResourceNotFoundException ex){
        BaseResponseDTO<Object> response = new BaseResponseDTO<>(
                false,
                ex.getMessage(),
                null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
