package autoinsurance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CoefficientNotFoundException extends RuntimeException {
    public CoefficientNotFoundException() {
    }

    public CoefficientNotFoundException(String message) {
        super(message);
    }

    public CoefficientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoefficientNotFoundException(Throwable cause) {
        super(cause);
    }

    public CoefficientNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
