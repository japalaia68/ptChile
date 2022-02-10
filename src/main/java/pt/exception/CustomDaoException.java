package pt.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Armando Guzman
 * @date 26/02/2019
 **/
@Getter
@Setter
public class CustomDaoException extends CustomException {


    public CustomDaoException() {
        super();
    }

    public CustomDaoException(String message) {
        super(message);
    }

    public CustomDaoException(String mensaje, HttpStatus httpStatus) {
        super(mensaje, httpStatus);
    }

    public CustomDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomDaoException(String message, Throwable cause, boolean enableSuppression,
                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public void setCodigo(String codigo) {
        super.setCodigoDefault("4-" + codigo);
    }
}
