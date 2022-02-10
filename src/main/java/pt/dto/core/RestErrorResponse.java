package pt.dto.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestErrorResponse extends RestResponse{


    @JsonProperty(value = "message")
    private String message;
    @JsonProperty(value = "description")
    private String description;
    @JsonProperty(value = "errors")
    private List<ErrorFielResponse> errors;

    private String codigo;
    private String trace;

    @JsonProperty(value = "extra")
    private String extra;

    public RestErrorResponse(String message) {
        this.message = message;
    }
}
