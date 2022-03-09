package ru.bilty.cover.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.bilty.cover.exception.BiltyCoverNotFoundException;
import ru.bilty.cover.model.Response;

@ControllerAdvice
public class BiltyControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BiltyCoverNotFoundException.class)
    public ResponseEntity<Response> handleBiltyCoverApplicationException(BiltyCoverNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Response> handleIllegalStateException(IllegalStateException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
