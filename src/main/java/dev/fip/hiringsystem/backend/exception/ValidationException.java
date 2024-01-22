package dev.fip.hiringsystem.backend.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationException extends ResponseEntityExceptionHandler {
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException ex,
    HttpHeaders headers,
    HttpStatusCode status,
    WebRequest request
  ) {
    Map<String, String> response = new HashMap<>();
    response.put("status", String.valueOf(status.value()));
    ex
      .getBindingResult()
      .getAllErrors()
      .forEach(error -> {
        String name = ((FieldError) error).getField();
        String value = error.getDefaultMessage();
        response.put(name, value);
      });
    return ResponseEntity.badRequest().body(response);
  }
}
