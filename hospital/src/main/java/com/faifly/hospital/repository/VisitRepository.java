package com.faifly.hospital.repository;

import com.faifly.hospital.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query(value = "select distinct count(*) from visit v " +
            "left join patients p on v.patient_id = p.id " +
            "left join doctor d on v.doctor_id = d.id " +
            "where v.start_date_time between :start and :end or " +
            "v.end_date_time between :start and :end or " +
            "v.start_date_time <= :start and v.end_date_time >= :end " +
            "group by v.id having count(*) > 0", nativeQuery = true)
    Long findByStartDateTimeAndEndDateTimeBetween(@Param("start") OffsetDateTime start, @Param("end") OffsetDateTime end);
}
