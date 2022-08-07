package tr.com.burakgul.profileapi.exception.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.profileapi.model.dto.ExceptionMessage;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionMessage> handleDataIntegrityException(DataIntegrityViolationException exception) {
        exception.printStackTrace();
        return ResponseEntity
                .internalServerError()
                .body(ExceptionMessage.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message("İlişkili alanlar ile ilgili bir hata tespit edildi. Lütfen Id değerlerini kontrol edin.")
                        .timestamp(new Date())
                        .build());
    }

    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<ExceptionMessage> handleResponseStatusException(ResponseStatusException responseStatusException) {
        return ResponseEntity.status(responseStatusException.getStatus())
                .body(
                        ExceptionMessage.builder()
                                .message(responseStatusException.getReason())
                                .status(responseStatusException.getStatus())
                                .timestamp(new Date())
                                .build());
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ExceptionMessage> handleExceptions(BadCredentialsException exception) {
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ExceptionMessage.builder()
                        .status(HttpStatus.UNAUTHORIZED)
                        .message("Kullanıcı adı veya parola hatalı.")
                        .timestamp(new Date()).build());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionMessage> handleExceptions(Exception exception) {
        exception.printStackTrace();
        return ResponseEntity
                .internalServerError()
                .body(ExceptionMessage.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message("Beklenmedik bir hata oluştu.")
                        .timestamp(new Date())
                        .build());
    }
}
