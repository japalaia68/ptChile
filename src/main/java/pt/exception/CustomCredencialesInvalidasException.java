package pt.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Armando Guzman
 * @date 26/02/2019
 **/
public class CustomCredencialesInvalidasException extends CustomException {


    public CustomCredencialesInvalidasException() {
        super("Credenciales invalidas", HttpStatus.UNAUTHORIZED);
        setCodigo("3");
    }


}
