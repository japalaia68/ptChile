package pt.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@Data

public class FormDataReqDto implements Serializable {


    private Integer countyFips;

    private String countyName;

    private String stateName;

    private String date;

    private Long countyVmt;

    private Long baselineJanVmt;

    private Double percentChangeFromJan;

    private Double mean7CountyVmt;

    private Double mean7PercentChangeFromJan;

    private String dateAtLow;

    private Double mean7CountyVmtAtLow;

    private Double percentChangeFromLow;
}




