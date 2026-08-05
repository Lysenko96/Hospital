package com.faifly.hospital.service;

import com.faifly.hospital.controller.VisitException;
import com.faifly.hospital.dto.RequestVisitDto;
import com.faifly.hospital.model.Doctor;
import com.faifly.hospital.model.Patient;
import com.faifly.hospital.model.Visit;
import com.faifly.hospital.repository.DoctorRepository;
import com.faifly.hospital.repository.PatientRepository;
import com.faifly.hospital.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class VisitService {

    private final VisitRepository visitRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public void createVisit(RequestVisitDto visitDto) {
        Optional<Patient> patient = patientRepository.findById(visitDto.getPatientId());
        if (patient.isEmpty()) {
            throw new NoSuchElementException("patientId is not exists");
        }
        Optional<Doctor> doctor = doctorRepository.findById(visitDto.getDoctorId());
        if (doctor.isEmpty()) {
            throw new NoSuchElementException("doctorId is not exists");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
        List<Visit> existsVisits = visitRepository.findByStartDateTimeAndEndDateTimeBetween(
                OffsetDateTime.parse(visitDto.getStart(), formatter),
                OffsetDateTime.parse(visitDto.getEnd(), formatter)
        );
        if (!existsVisits.isEmpty()) {
            throw new VisitException("Visit between start and end exists");
        }
        visitRepository.save(Visit.builder()
                .startDateTime(OffsetDateTime.parse(visitDto.getStart(), formatter))
                .endDateTime(OffsetDateTime.parse(visitDto.getEnd(), formatter))
                .patient(patient.get())
                .doctor(doctor.get())
                .build());
    }

}
