package com.faifly.hospital.repository;

import com.faifly.hospital.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("select p from Patient p left join fetch p.visits v left join fetch v.doctor")
    List<Patient> findAll();
}
