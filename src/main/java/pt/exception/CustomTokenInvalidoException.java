package pt.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Armando Guzman
 * @date 26/02/2019
 **/
public class CustomTokenInvalidoException extends CustomException {


    public CustomTokenInvalidoException() {
        super("El token es invalido o ya ha expirado", HttpStatus.UNAUTHORIZED);
        setCodigo("5");
    }


}
