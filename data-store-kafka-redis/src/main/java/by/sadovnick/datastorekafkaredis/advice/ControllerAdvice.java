package by.sadovnick.datastorekafkaredis.advice;

import by.sadovnick.datastorekafkaredis.exception.SensorNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(SensorNotFoundException.class)
    public String sensorNotFoundExceptionHandler(SensorNotFoundException e) {
        return "Sensor not found";
    }

    @ExceptionHandler
    public String server(Exception e){
        log.error("Error: {}", e.getMessage());
        return "Something happened.";
    }
}
