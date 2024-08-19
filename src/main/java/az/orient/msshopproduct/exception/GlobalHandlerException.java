package az.orient.msshopproduct.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var fieldExceptions = ex.getFieldErrors().stream().map(f -> FieldException.builder().fieldName(f.getField()).errorMessage(f.getDefaultMessage()).build()).toList();
        var result = ExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.toString())
                .fieldExceptions(fieldExceptions)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @ExceptionHandler
    public ResponseEntity handleBrandIdNotFoundException(FeignException ex) {

        var result = ExceptionResponse.builder().code(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    @ExceptionHandler(CategoryIdNotFoundException.class)
    public ResponseEntity handleCategoryIdNotFoundException(CategoryIdNotFoundException ex) {
        var result = ExceptionResponse.builder().code(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    @ExceptionHandler(ProductIdNotFoundException.class)
    public ResponseEntity handleProductIdNotFoundException(ProductIdNotFoundException ex) {
        var result = ExceptionResponse.builder().code(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    @ExceptionHandler(ModelIdNotFoundException.class)
    public ResponseEntity handleModelIdNotFoundException(ModelIdNotFoundException ex) {
        var result = ExceptionResponse.builder().code(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }


}
