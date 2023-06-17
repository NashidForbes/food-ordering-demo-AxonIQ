package io.axoniq.foodordering.command.handler.errorhandling;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

@EnableWebMvc
@ControllerAdvice
public class ProductsServiceExceptionErrorHandler extends ResponseEntityExceptionHandler {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @ExceptionHandler(value= {IllegalStateException.class})
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex, WebRequest request) {

        ErrorMessage errorMessage = new ErrorMessage(dateFormat.format(new Date()), ex.getMessage());

        return new ResponseEntity<Object>(errorMessage, new HttpHeaders(),  HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value= {Exception.class})
    public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {

        ErrorMessage errorMessage = new ErrorMessage(dateFormat.format(new Date()), ex.getMessage());

        return new ResponseEntity<Object>(errorMessage, new HttpHeaders(),  HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
