package com.exalt.katas.application.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.exalt.katas.domain.exception.InvalidMontantException;
import com.exalt.katas.domain.exception.SoldeInsuffisantException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {


  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handle5xx(Exception exception, WebRequest webRequest) {

    ErrorResponse errorResponse = ErrorResponse.builder()
        .errorMessage(INTERNAL_SERVER_ERROR.getReasonPhrase())
        .errorCode(INTERNAL_SERVER_ERROR.value())
        .build();
    return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(InvalidMontantException.class)
  public ResponseEntity<ErrorResponse> handleInvalidMontantException(InvalidMontantException invalidMontantException,
      WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .errorMessage(invalidMontantException.getMessage())
        .errorCode(4000)
        .build();
    return new ResponseEntity<>(errorResponse, BAD_REQUEST);
  }

  @ExceptionHandler(SoldeInsuffisantException.class)
  public ResponseEntity<ErrorResponse> handleSoldeInsuffisantException(
      SoldeInsuffisantException soldeInsuffisantException, WebRequest request) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .errorMessage(soldeInsuffisantException.getMessage())
        .errorCode(4001)
        .build();
    return new ResponseEntity<>(errorResponse, BAD_REQUEST);
  }
}
