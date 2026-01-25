package projecttest.javaspringtest.Controller;


import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * <h2>1. 특정 패키지만 적용</h2>
 * {@code @ControllerAdvice(basePackages = "com.example.controller.api")}} <br>
 *
 *
 * <h2>2. 특정 어노테이션이 붙은 컨트롤러만 적용</h2>
 * {@code @ControllerAdvice(annotations = RestController.class)} <br>
 *
 *
 * <h2>3. 특정 클래스만 적용</h2>
 * {@code @ControllerAdvice(assignableTypes = {UserController.class, OrderController.class})} <br>
 *
 */
@ControllerAdvice
public class SimpleControllerAdvice {

    /**
     * {@code @ExceptionHandler(produces = "application/json")} : 반환 미디어 타입 지정가능 <br>
     * {@code @ExceptionHandler(IllegalArgumentException.class)} : 일반적 예외 처리 <br>
     * {@code @ExceptionHandler({IllegalArgumentException.class , IOException.class })} : 여러가지 예외 처리가능 <br>
     */
    // 특정 예외에 대한 처리
    @Order(1)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> IllegalArgumentException() {
        return ResponseEntity.badRequest().build();
    }

    // 그 외 모든 예외를 처리하는 폴백(fallback) 메서드
    @Order(2)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}