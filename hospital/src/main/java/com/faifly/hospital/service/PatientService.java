package com.faifly.hospital.service;

import com.faifly.hospital.dto.DoctorDto;
import com.faifly.hospital.dto.PatientData;
import com.faifly.hospital.dto.ResponsePatientDto;
import com.faifly.hospital.dto.VisitDto;
import com.faifly.hospital.model.Doctor;
import com.faifly.hospital.model.Patient;
import com.faifly.hospital.model.Visit;
import com.faifly.hospital.repository.DoctorRepository;
import com.faifly.hospital.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private Map<Long, Integer> patientsSizeByDoctorId = new HashMap<>();

    public ResponsePatientDto getPatientsByFilter(String search, List<Long> doctorIds) {
        List<Doctor> doctors = doctorRepository.findAll();
        patientsSizeByDoctorId = doctors.stream().collect(toMap(Doctor::getId, d -> d.getPatients().size()));
        List<Patient> patients = new ArrayList<>();
        if (search != null && doctorIds != null) {
            patients = patientRepository.findByNameAndDoctorIds(search, doctorIds);
        } else if (search != null) {
            patients = patientRepository.findByName(search);
        } else if (doctorIds != null) {
            patients = patientRepository.findByDoctorIds(doctorIds);
        }
        return ResponsePatientDto.builder()
                .data(patients.stream()
                        .map(p -> PatientData.builder()
                                .firstName(p.getFirstName())
                                .lastName(p.getLastName())
                                .lastVisits(toVisitDto(p.getVisits()))
                                .build())
                        .collect(toList()))
                .count(patients.size())
                .build();
    }

    public ResponsePatientDto getAllPatients() {
        List<Doctor> doctors = doctorRepository.findAll();
        patientsSizeByDoctorId = doctors.stream().collect(toMap(Doctor::getId, d -> d.getPatients().size()));
        List<Patient> patients = patientRepository.findAll();
        return ResponsePatientDto.builder()
                .data(patients.stream()
                        .map(p -> PatientData.builder()
                                .firstName(p.getFirstName())
                                .lastName(p.getLastName())
                                .lastVisits(toVisitDto(p.getVisits()))
                                .build())
                        .collect(toList()))
                .count(patients.size())
                .build();
    }

    private List<VisitDto> toVisitDto(List<Visit> visits) {
        return visits.stream().map(v -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z")
                            .withZone(v.getDoctor().getTimezone().toZoneId());
                    return VisitDto.builder()
                            .start(formatter.format(v.getStartDateTime()))
                            .end(formatter.format(v.getEndDateTime()))
                            .doctor(toDoctorDto(v.getDoctor()))
                            .build();
                })
                .collect(toList());
    }

    private DoctorDto toDoctorDto(Doctor doctor) {
        return DoctorDto.builder()
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .totalPatients(patientsSizeByDoctorId.get(doctor.getId()))
                .build();
    }


}
