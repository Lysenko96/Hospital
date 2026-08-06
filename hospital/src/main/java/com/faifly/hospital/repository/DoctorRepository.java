package com.faifly.hospital.repository;

import com.faifly.hospital.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("select distinct d from Doctor d join fetch d.patients")
    List<Doctor> findAll();
}
