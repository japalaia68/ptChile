package pt.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Armando Guzman
 * @date 26/02/2019
 **/
public class CustomTokenCaptchaInvalidoException extends CustomException {


    public CustomTokenCaptchaInvalidoException() {
        super("Captcha/ Verificación de seguridad invalida", HttpStatus.UNAUTHORIZED);
        setCodigo("4");
    }


}
