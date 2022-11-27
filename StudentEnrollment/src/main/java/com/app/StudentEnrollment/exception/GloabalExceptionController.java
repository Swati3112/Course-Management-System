package com.app.StudentEnrollment.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(1)
@RestControllerAdvice
public class GloabalExceptionController extends ResponseEntityExceptionHandler {
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDate.now());
		body.put("status", status.value());
		if (ex.getFieldErrors().size() > 0) {
			List<String> errors = ex.getFieldErrors().stream().map(x -> x.getDefaultMessage())
					.collect(Collectors.toList());
			body.put("errors", errors);
			return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
		} else {
			List<String> errors = ex.getAllErrors().stream().map(x -> x.getDefaultMessage())
					.collect(Collectors.toList());
			body.put("errors", errors);
			return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ExceptionHandler({ Exception.class })
	protected ResponseEntity<Object> handleException(Exception ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDate.now());
		body.put("status", 500);
		List<String> errors = Arrays.asList(ex.getMessage());
		ex.printStackTrace();
		body.put("errors", errors);
		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(ExceptionOccured.class)
	public ResponseEntity<Object> handleNotFoundException(ExceptionOccured e, WebRequest w) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", e.getMessage());

		return new ResponseEntity<>(body, e.status);
	}

}
