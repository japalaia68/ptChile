package pt.exception;

import org.springframework.http.HttpStatus;

public class CampoCsvException extends Exception {

    private static final long serialVersionUID = -2294715588831928937L;
    private String domain;
    private HttpStatus httpStatus;


    public CampoCsvException(HttpStatus httpStatus, String message, String domain) {
        super(message);
        this.domain = domain;
        this.httpStatus = httpStatus;
    }

    public CampoCsvException() {
        super();
    }

    public CampoCsvException(String message) {
        super(message);
    }

    public CampoCsvException(String message, Throwable cause) {
        super(message, cause);
    }

    public CampoCsvException(Throwable cause) {
        super(cause);
    }

    public CampoCsvException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CampoCsvException(String message, HttpStatus httpStatus) {
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
