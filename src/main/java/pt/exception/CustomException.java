package pt.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Armando Guzman
 * @date 26/02/2019
 **/
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomException extends Exception {

    private String idRespuesta;
    private Object object;
    private HttpStatus httpStatus;
    private String codigo = "2-";

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(String message, Throwable cause, boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CustomException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public CustomException(String message, HttpStatus httpStatus, String codigo, String idRespuesta) {
        super(message);
        this.httpStatus = httpStatus;
        setCodigo(codigo);
        setIdRespuesta(idRespuesta);
    }

    public CustomException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    public CustomException(Throwable cause, HttpStatus httpStatus) {
        super(cause);
        this.httpStatus = httpStatus;
    }

    public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus httpStatus) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.httpStatus = httpStatus;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setCodigo(String codigo) {
        this.codigo = "2-" + codigo;
    }

    public void setCodigoDefault(String codigo) {
        this.codigo = codigo;
    }
}
