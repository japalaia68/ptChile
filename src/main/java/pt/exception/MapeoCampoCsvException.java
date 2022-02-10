package pt.exception;

import org.springframework.http.HttpStatus;

public class MapeoCampoCsvException extends Exception {


    /**
     *
     */
    private static final long serialVersionUID = -2294715588831928937L;
    private String domain;
    private HttpStatus httpStatus;

    public MapeoCampoCsvException(HttpStatus httpStatus, String message, String domain) {
        super(message);
        this.domain = domain;
        this.httpStatus = httpStatus;
    }

    public MapeoCampoCsvException() {
        super();
    }

    public MapeoCampoCsvException(String message) {
        super(message);
    }

    public MapeoCampoCsvException(String message, Throwable cause) {
        super(message, cause);
    }

    public MapeoCampoCsvException(Throwable cause) {
        super(cause);
    }

    public MapeoCampoCsvException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getDomain() {
        return domain;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
