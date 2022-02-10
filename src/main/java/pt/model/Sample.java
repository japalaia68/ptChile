package pt.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@JsonIgnoreProperties(ignoreUnknown = true)
//wde_recibos
@Table(name = "SAMPLE")
@ToString
public class Sample implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "county_fips")
    private Integer countyFips;


    @Column(name = "county_name")
    private String countyName;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "date")
    private String date;

    @Column(name = "county_vmt")
    private Long countyVmt;

    @Column(name = "baseline_jan_vmt")
    private Long baselineJanVmt;

    @Column(name = "percent_change_from_jan")
    private Double percentChangeFromJan;

    @Column(name = "mean7_county_vmt")
    private Double mean7CountyVmt;

    @Column(name = "mean7_percent_change_from_jan")
    private Double mean7PercentChangeFromJan;

    @Column(name = "date_at_low")
    private String dateAtLow;

    @Column(name = "mean7_county_vmt_at_low")
    private Double mean7CountyVmtAtLow;

    @Column(name = "percent_change_from_low")
    private Double percentChangeFromLow;

}
