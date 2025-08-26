package fawry.task.admin.dashboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler extends RuntimeException {
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<?> handleNotFound(NotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error(ex.getMessage()));
  }


  @ExceptionHandler(ApiException.class)
  public ResponseEntity<?> handleApi(ApiException ex) {
    return ResponseEntity.badRequest().body(error(ex.getMessage()));
  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));
    return ResponseEntity.badRequest().body(errors);
  }


  private Map<String, Object> error(String msg) {
    Map<String, Object> map = new HashMap<>();
    map.put("error", msg);
    return map;
  }
}
