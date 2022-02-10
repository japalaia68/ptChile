package pt.model;


import com.opencsv.bean.CsvBindByName;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class SampleCsv implements Serializable {

    @CsvBindByName(column = "county_fips")
    private Integer countyFips;

    @CsvBindByName(column = "county_name")
    private String countyName;

    @CsvBindByName(column = "state_name")
    private String stateName;

    @CsvBindByName(column = "date")
    private String date;

    @CsvBindByName(column = "county_vmt")
    private Long countyVmt;

    @CsvBindByName(column = "baseline_jan_vmt")
    private Long baselineJanVmt;

    @CsvBindByName(column = "percent_change_from_jan")
    private Double percentChangeFromJan;

    @CsvBindByName(column = "mean7_county_vmt")
    private Double mean7CountyVmt;

    @CsvBindByName(column = "mean7_percent_change_from_jan")
    private Double mean7PercentChangeFromJan;

    @CsvBindByName(column = "date_at_low")
    private String dateAtLow;

    @CsvBindByName(column = "mean7_county_vmt_at_low")
    private Double mean7CountyVmtAtLow;

    @CsvBindByName(column = "percent_change_from_low")
    private Double percentChangeFromLow;

}
