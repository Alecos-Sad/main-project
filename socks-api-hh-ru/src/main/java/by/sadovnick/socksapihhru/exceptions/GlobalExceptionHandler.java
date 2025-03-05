package by.sadovnick.socksapihhru.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundSocksException.class)
    public ResponseEntity<String> handleNotFoundSocksException(NotFoundSocksException e) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(e.getMessage());
    }
}
