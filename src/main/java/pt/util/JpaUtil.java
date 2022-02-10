package pt.util;

import pt.exception.CustomServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class JpaUtil<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaUtil.class);

    /**
     * Chequea que un optional este presente, en caso de estarlo lo retorna y si no retorna una Excepcion con
     * Codigo HTTP 409 CONFLIC
     *
     * @param optional
     * @param message
     * @return
     * @throws CustomServiceException
     */
    public T valueOfOptional(Optional<T> optional, String message) throws CustomServiceException {
        if (optional.isPresent()) {
            return optional.get();
        } else {
            LOGGER.error(message);
            throw new CustomServiceException(message, HttpStatus.CONFLICT);
        }
    }


}
