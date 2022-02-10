package pt.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.dto.request.FormDataReqDto;
import pt.model.Sample;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import java.util.Date;


@Repository("sampleRepository")
public interface SampleRepository extends JpaRepository<Sample, Long> {
    @Query(value =

            "select d From Sample as d "+
                    " where ((:#{#request.countyName} is null) or (d.countyName like  ( '%' || :#{#request.countyName} || '%')  ))"
                    +" AND ((:#{#request.stateName} is null) or (d.stateName like  ( '%' || :#{#request.stateName} || '%')  ))"
                    +" AND ((:#{#request.countyFips} is null) or (d.countyFips = :#{#request.countyFips}  ))"
                    +" AND ((:#{#request.date} is null) or (d.date like  ( '%' || :#{#request.date} || '%')  ))"
                   + " AND ((:#{#request.countyVmt} is null) or (d.countyVmt = :#{#request.countyVmt}  ))"
                   + " AND ((:#{#request.baselineJanVmt} is null) or (d.baselineJanVmt = :#{#request.baselineJanVmt}  ))"
                   + " AND ((:#{#request.percentChangeFromJan} is null) or (d.percentChangeFromJan = :#{#request.percentChangeFromJan}  ))"
                  + " AND ((:#{#request.mean7CountyVmt} is null) or (d.mean7CountyVmt = :#{#request.mean7CountyVmt}  ))"
                  + " AND ((:#{#request.mean7PercentChangeFromJan} is null) or (d.mean7PercentChangeFromJan = :#{#request.mean7PercentChangeFromJan}  ))"
                   + " AND ((:#{#request.dateAtLow} is null) or (d.dateAtLow like  ( '%' || :#{#request.dateAtLow} || '%')  ))"
                   + " AND ((:#{#request.mean7CountyVmtAtLow} is null) or (d.mean7CountyVmtAtLow = :#{#request.mean7CountyVmtAtLow}  ))"
                   + " AND ((:#{#request.percentChangeFromLow} is null) or (d.percentChangeFromLow = :#{#request.percentChangeFromLow}  ) )"

    )
    Page<Sample> findAllByRequest(@Param("request") FormDataReqDto request, Pageable pageable);


}