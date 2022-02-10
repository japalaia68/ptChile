package pt.config;

import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import pt.dto.core.ErrorFielResponse;
import pt.dto.core.RestErrorResponse;
import pt.dto.generic.ErrorArchivoResponse;
import pt.dto.generic.ErrorResponse;
import pt.dto.generic.RestResponseObjectDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pt.exception.*;


import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Validated
public class RestResponseExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseExceptionHandler.class);

    @ExceptionHandler(value = {CampoCsvException.class})
    public ResponseEntity<RestResponseObjectDto> invalidFormatException(CampoCsvException e) {
        HttpStatus httpStatus;
        httpStatus = HttpStatus.BAD_REQUEST;
        LOGGER.error(e.getStackTrace()[0].getMethodName() + e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setCause(e.getMessage());
        RestResponseObjectDto restErrorResponse = new RestResponseObjectDto(httpStatus, errorResponse);
        return ResponseEntity.status(httpStatus).body(restErrorResponse);
    }
    @ExceptionHandler(value = {BindException .class})
    public ResponseEntity<RestErrorResponse> handleValidationFailure(BindException  ex) {
        String mensage = "Error de entrada de datos";

        RestErrorResponse restErrorResponse = new RestErrorResponse(mensage);
        List<ErrorFielResponse> errorFielResponses = new ArrayList<>();

        List<org.springframework.validation.FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (org.springframework.validation.FieldError fieldError : fieldErrors) {
            errorFielResponses.add(new ErrorFielResponse(123, fieldError.getField(), fieldError.getDefaultMessage()));
        }

        restErrorResponse.setErrors(errorFielResponses);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorResponse);

    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<RestErrorResponse> handleValidationFailure(MethodArgumentNotValidException ex) {
        String mensage = "Error de entrada de datos";

        RestErrorResponse restErrorResponse = new RestErrorResponse(mensage);
        List<ErrorFielResponse> errorFielResponses = new ArrayList<>();

        List<org.springframework.validation.FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (org.springframework.validation.FieldError fieldError : fieldErrors) {
            errorFielResponses.add(new ErrorFielResponse(123, fieldError.getField(), fieldError.getDefaultMessage()));
        }

        restErrorResponse.setErrors(errorFielResponses);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorResponse);

//        RestResponse restResponse=new RestResponse(HttpStatus.BAD_REQUEST,restErrorResponse);
//        return ResponseEntity.badRequest().body(restResponse);
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<RestResponseObjectDto> throwGenericException(Exception e) {
        HttpStatus httpStatus;
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        LOGGER.error(e.getStackTrace()[0].getMethodName() + e.getMessage());
        e.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse();
  //    errorResponse.setMessage(e.getMessage());
  //    errorResponse.setCause(e.getMessage());
        errorResponse.setMessage("Error Interno");
       errorResponse.setCause("Error Interno");
        RestResponseObjectDto restErrorResponse = new RestResponseObjectDto(httpStatus, errorResponse);
        return ResponseEntity.status(httpStatus).body(restErrorResponse);
    }

    @ExceptionHandler(value = {ArchivoLogException.class})
    public ResponseEntity<RestResponseObjectDto> throwArchivoException(ArchivoLogException e) {
        HttpStatus httpStatus;
        httpStatus = HttpStatus.BAD_REQUEST;
        LOGGER.error(e.getStackTrace()[0].getMethodName() + e.getMessage());
        ErrorArchivoResponse errorResponse = new ErrorArchivoResponse();
        errorResponse.setMessage("Se produjo un error en la validación del archivo");
        e.printStackTrace();

        errorResponse.setIdArchivo(e.getMessage());
        RestResponseObjectDto restErrorResponse = new RestResponseObjectDto(httpStatus, errorResponse);
        return ResponseEntity.status(httpStatus).body(restErrorResponse);
    }


    @ExceptionHandler(value = {CustomException.class, CustomDaoException.class, CustomServiceException.class})
    public ResponseEntity<RestErrorResponse> customExceptions(CustomException e) {
        HttpStatus httpStatus = e.getHttpStatus() != null ? e.getHttpStatus() : HttpStatus.INTERNAL_SERVER_ERROR;
        e.printStackTrace();
        RestErrorResponse restErrorResponse = new RestErrorResponse();
        restErrorResponse.setStatus(httpStatus.getReasonPhrase());
        restErrorResponse.setStatusCode(httpStatus.value());
        restErrorResponse.setBody(e.getMessage());
        restErrorResponse.setMessage(e.getMessage());
        restErrorResponse.setCodigo(e.getCodigo());
        if (e.getIdRespuesta() != null)
            restErrorResponse.setExtra(e.getIdRespuesta());

        if(e.getObject()!=null)
            restErrorResponse.setErrors((List<ErrorFielResponse>) e.getObject());
        if (httpStatus.equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
            LOGGER.error(e.getMessage(), e);
//            String stacktrace =ExceptionUtils.getStackTrace(e);
//            restErrorResponse.setTrace(stacktrace);
            restErrorResponse.setMessage("Error interno, comuníquese con el administrador del sistema.");
        } else {
            LOGGER.error(e.getMessage());
        }
        return ResponseEntity.status(httpStatus).body(restErrorResponse);

    }


}
