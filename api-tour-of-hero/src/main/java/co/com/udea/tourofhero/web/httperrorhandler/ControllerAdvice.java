package co.com.udea.tourofhero.web.httperrorhandler;

import co.com.udea.tourofhero.domain.errorhandler.BadResponseErrorHandler;
import co.com.udea.tourofhero.domain.model.Mensaje;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = BadResponseErrorHandler.class)
    public ResponseEntity<Mensaje> respuestaFallida(BadResponseErrorHandler badResponseHandler) {
        Mensaje mensaje = new Mensaje(badResponseHandler.getCode(), badResponseHandler.getMessage());
        return new ResponseEntity<>(mensaje, badResponseHandler.getHttpStatus());
    }
}
