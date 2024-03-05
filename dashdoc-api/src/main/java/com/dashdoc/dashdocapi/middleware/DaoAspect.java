package com.dashdocnow.middleware;

import com.dashdocnow.utils.ErrorResponse;
import com.dashdocnow.utils.NotFoundException;
import jdk.jfr.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.HibernateException;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Calendar;

@Aspect
public class DaoAspect {
    private static Logger logger = LogManager.getLogger(DaoAspect.class);

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> notFoundAdvice(HibernateException ex){
        ErrorResponse err = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                Calendar.getInstance()
        );

        return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @Description("HTTP 500: Catchall for Hibernate Errors")
    public ResponseEntity<ErrorResponse> genericHibernateAdvice(GenericJDBCException ex){

        ErrorResponse err = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                Calendar.getInstance()
        );

        return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
    }
}
