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

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private Map<Long, Integer> patientsSizeByDoctorId = new HashMap<>();

    public void getPatientsByFilter() {

    }

    public ResponsePatientDto getAllPatients() {
        List<Doctor> doctors = doctorRepository.findAll();
        patientsSizeByDoctorId = doctors.stream().collect(toMap(Doctor::getId, d -> d.getPatients().size()));
        List<Patient> patients = patientRepository.findAll();
        List<PatientData> patientData = patients.stream()
                .map(p -> PatientData.builder()
                        .firstName(p.getFirstName())
                        .lastName(p.getLastName())
                        .lastVisits(toVisitDto(p.getVisits()))
                        .build())
                .collect(toList());
        ResponsePatientDto responsePatientDto = ResponsePatientDto.builder()
                .data(patientData)
                .count(patients.size())
                .build();
        return responsePatientDto;
    }

    private List<VisitDto> toVisitDto(List<Visit> visits) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
        return visits.stream().map(v -> VisitDto.builder()
                        .start(formatter.format(v.getStartDateTime()))
                        .end(formatter.format(v.getEndDateTime()))
                        .doctor(toDoctorDto(v.getDoctor()))
                        .build())
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
