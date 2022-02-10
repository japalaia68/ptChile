package pt.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Armando Guzman
 * @date 26/02/2019
 **/
public class CustomServiceException extends CustomException {


    public CustomServiceException() {
        super();
    }

    public CustomServiceException(String message) {
        super(message);
    }

    public CustomServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomServiceException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CustomServiceException(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public CustomServiceException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public CustomServiceException(String message, HttpStatus httpStatus, String codigo,Object o) {
        super(message, httpStatus);
        setCodigo(codigo);
        setObject(o);
    }
    public CustomServiceException(String message, HttpStatus httpStatus, String codigo) {
        super(message, httpStatus);
        setCodigo(codigo);
    }

    public CustomServiceException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause, httpStatus);
    }

    public CustomServiceException(Throwable cause, HttpStatus httpStatus) {
        super(cause, httpStatus);
    }

    public CustomServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus httpStatus) {
        super(message, cause, enableSuppression, writableStackTrace, httpStatus);
    }

    @Override
    public void setCodigo(String codigo) {
        super.setCodigoDefault("3-" + codigo);
    }
}
