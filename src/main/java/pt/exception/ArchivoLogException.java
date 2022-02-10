package pt.exception;

import org.springframework.http.HttpStatus;

public class ArchivoLogException extends Exception {


    private static final long serialVersionUID = -2294715588831928937L;
    private String domain;
    private HttpStatus httpStatus;


    public ArchivoLogException(HttpStatus httpStatus, String message, String domain) {
        super(message);
        this.domain = domain;
        this.httpStatus = httpStatus;
    }

    public ArchivoLogException() {
        super();
    }

    public ArchivoLogException(String message) {
        super(message);
    }

    public ArchivoLogException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArchivoLogException(Throwable cause) {
        super(cause);
    }

    public ArchivoLogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ArchivoLogException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public String getDomain() {
        return domain;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
