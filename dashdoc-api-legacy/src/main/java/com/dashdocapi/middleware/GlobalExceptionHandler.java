package com.dashdocapi.middleware;

import com.dashdocapi.utils.*;
import com.stripe.exception.StripeException;
import jakarta.servlet.ServletException;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.LogManager;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Calendar;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> notFoundAdvice(NotFoundException ex){
        var trace = ex.getStackTrace()[0];
        var errorPath = trace.getClassName() + "." + trace.getMethodName();

        ErrorResponse err = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                errorPath,
                ex.getMessage(),
                Calendar.getInstance()
        );

        logger.error(err);
        return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> badRequestAdvice(BadRequestException ex){
        var trace = ex.getStackTrace()[0];
        var errorPath = trace.getClassName() + "." + trace.getMethodName();

        ErrorResponse err = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                errorPath,
                ex.getMessage(),
                Calendar.getInstance(),
                ex.getCause().toString()
        );

        logger.error(err);
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> serverErrorAdvice(HttpServerErrorException.InternalServerError ex){
        var trace = ex.getStackTrace()[0];
        var errorPath = trace.getClassName() + "." + trace.getMethodName();

        ErrorResponse err = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                errorPath,
                "There was an error with your request. Please try again or contact the administrator.",
                Calendar.getInstance()
        );

        logger.error(err);
        return new ResponseEntity<>(err,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> badServletRequestAdvice(ServletException ex){
        ErrorResponse err = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                Calendar.getInstance()
        );

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> enumExceptionHandler(HttpMessageNotReadableException ex){
        String errorMessage = ex.getMessage();

        // covers for a bad UserType value
        if (errorMessage.contains("com.dashdocapi.interfaces.enums.UserType")) {
            errorMessage = "The given input is not a valid choice for UserType";
        }

        ErrorResponse err = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                errorMessage,
                Calendar.getInstance()
        );

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> invalidInputHandler(MethodArgumentNotValidException ex){
        String detailedMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findAny().get().toString();

        ErrorResponse err = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                detailedMessage,
                Calendar.getInstance()
        );
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> stripeExceptionAdvice(StripeException ex){
        ErrorResponse err = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                Calendar.getInstance()
        );

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }
}
