package pt.dto.generic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponseDto implements Serializable {
    private static final long serialVersionUID = 3928270002376365195L;
    private Integer statusCode;
    private String status;

    public RestResponseDto(HttpStatus httpStatus) {
        this.statusCode = httpStatus.value();
        this.status = httpStatus.getReasonPhrase();
    }
}
