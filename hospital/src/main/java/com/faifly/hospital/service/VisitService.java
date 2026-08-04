package com.faifly.hospital.service;

import com.faifly.hospital.dto.RequestVisitDto;
import com.faifly.hospital.model.Doctor;
import com.faifly.hospital.model.Patient;
import com.faifly.hospital.model.Visit;
import com.faifly.hospital.repository.DoctorRepository;
import com.faifly.hospital.repository.PatientRepository;
import com.faifly.hospital.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VisitService {

    private final VisitRepository visitRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public void save(RequestVisitDto visitDto) {
        Optional<Patient> patient = patientRepository.findById(Long.valueOf(visitDto.getPatientId()));
        Optional<Doctor> doctor = doctorRepository.findById(Long.valueOf(visitDto.getDoctorId()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
        List<Visit> existsVisits = visitRepository.findByStartDateTimeAndEndDateTimeBetween(
                OffsetDateTime.parse(visitDto.getStart(), formatter),
                OffsetDateTime.parse(visitDto.getEnd(), formatter)
        );
        System.out.println("#####EXISTSVISITS");
        System.out.println(existsVisits);
        if (!existsVisits.isEmpty()) {
            log.info("visit by time exists");
            return;
        }
        Visit visit = new Visit(OffsetDateTime.parse(visitDto.getStart(), formatter),
                OffsetDateTime.parse(visitDto.getEnd(), formatter),
                patient.orElseThrow(), doctor.orElseThrow());
        visitRepository.save(visit);
    }

}
