package megalab.cinematica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> createEntityEx(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({FindByIdException.class})
    public ResponseEntity<?> findByIdEx(FindByIdException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler({DeleteException.class})
    public ResponseEntity<?> findByIdEx(DeleteException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler({TokenValidationException.class})
    public ResponseEntity<?> tokenEx(TokenValidationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
