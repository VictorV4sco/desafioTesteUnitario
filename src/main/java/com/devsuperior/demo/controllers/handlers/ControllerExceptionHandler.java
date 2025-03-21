package com.devsuperior.demo.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.demo.dto.CustomErrorDTO;
import com.devsuperior.demo.services.exceptions.DataBaseException;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<CustomErrorDTO> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
	        HttpStatus status = HttpStatus.NOT_FOUND;
	        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
	        return ResponseEntity.status(status).body(err);
	    }

	    @ExceptionHandler(DataBaseException.class)
	    public ResponseEntity<CustomErrorDTO> database(DataBaseException e, HttpServletRequest request) {
	        HttpStatus status = HttpStatus.BAD_REQUEST;
	        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
	        return ResponseEntity.status(status).body(err);
	    }
}
