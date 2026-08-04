package com.faifly.hospital.repository;

import com.faifly.hospital.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    Visit findByStartDateTimeAndEndDateTime(OffsetDateTime start, OffsetDateTime end);

    @Query("select v from Visit v join fetch v.patient and join fetch v.doctor where startDateTime between :start and :end or endDateTime between :start and :end")
    List<Visit> findByStartDateTimeAndEndDateTimeBetween(OffsetDateTime start, OffsetDateTime end);
}
