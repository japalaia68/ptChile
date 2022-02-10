package pt.dto.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse<T> implements Serializable {

  private static final long serialVersionUID = 2476241489347273305L;

  private Integer statusCode;
  private String status;
  private T body;

  public RestResponse(HttpStatus httpStatus, T body ) {
    this.statusCode = httpStatus.value();
    this.status = httpStatus.getReasonPhrase();
    this.body = body;
  }

}
