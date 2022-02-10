package pt.exception;

/**
 * @date 09/10/2018
 **/
public class CustomMapperException extends Exception {

    private static final long serialVersionUID = -1934710197655255319L;

    public CustomMapperException() {
        super();
    }

    public CustomMapperException(String message) {
        super(message);
    }

    /*
        public CustomMapperException(HttpStatus httpStatus, String message, Integer code, String description) {
            super(httpStatus,message,code,description);

        }*/
    public CustomMapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomMapperException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
