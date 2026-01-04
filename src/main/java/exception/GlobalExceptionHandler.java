package exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleBadRequest(
            IllegalArgumentException ex) {

        return Mono.just(
                ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse(
                                HttpStatus.BAD_REQUEST.value(),
                                ex.getMessage()
                        ))
        );
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGeneric(Exception ex) {

        return Mono.just(
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ErrorResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "Error interno del servidor"
                        ))
        );
    }
}
