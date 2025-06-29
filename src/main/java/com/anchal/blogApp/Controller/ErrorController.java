package com.anchal.blogApp.Controller;

import com.anchal.blogApp.Model.DTO.APIErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIErrorResponse> handleException(Exception e) {
        log.error("Caught Exeption " + e);
        APIErrorResponse apiErrorResponse = APIErrorResponse.builder().
                status(HttpStatus.INTERNAL_SERVER_ERROR.value()).
                message("Error message " + e.getMessage()).
                build();

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<APIErrorResponse> handleIllegalArgException(IllegalArgumentException ex) {
        log.error(ex.getMessage());
        APIErrorResponse apiErrorResponse = APIErrorResponse.builder().
                status(HttpStatus.BAD_REQUEST.value()).
                message("Bad Request : " + ex.getMessage()).
                build();
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<APIErrorResponse> handleIllegalStateException(IllegalStateException ex) {
        log.error("exception is {}", ex.getMessage());
        APIErrorResponse apiErrorResponse = APIErrorResponse.builder().
                status(HttpStatus.CONTINUE.value()).
                message(ex.getMessage()).build();

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<APIErrorResponse> handleInternalAuthenticationServiceException(BadCredentialsException e) {
        log.error(e.getMessage() + "insise bad creatinals ");
        APIErrorResponse apiErrorResponse = APIErrorResponse.builder().
                status(HttpStatus.UNAUTHORIZED.value()).
                message(e.getMessage()).
                build();
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.UNAUTHORIZED);
    }

}
