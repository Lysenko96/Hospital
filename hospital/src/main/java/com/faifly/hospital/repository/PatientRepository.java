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

    @Query("select p from Patient p left join fetch p.visits v left join fetch v.doctor where p.firstName = :name")
    List<Patient> findByName(String name);

    @Query("select p from Patient p left join fetch p.visits v left join fetch v.doctor d where d.id in :doctorIds")
    List<Patient> findByDoctorIds(List<Long> doctorIds);

    @Query("select p from Patient p left join fetch p.visits v left join fetch v.doctor d where p.firstName = :name or d.id in :doctorIds")
    List<Patient> findByNameAndDoctorIds(String name, List<Long> doctorIds);
}
