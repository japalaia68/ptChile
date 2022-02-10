package pt.dto.generic;

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
public class RestResponseObjectDto extends RestResponseDto implements Serializable {

    private static final long serialVersionUID = -6295324801768190127L;
    private Object body;

    public RestResponseObjectDto(HttpStatus httpStatus, Object body) {
        super(httpStatus);
        this.body = body;
    }
}
