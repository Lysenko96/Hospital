package com.faifly.hospital.controller;

import com.faifly.hospital.dto.RequestVisitDto;
import com.faifly.hospital.dto.ResponsePatientDto;
import com.faifly.hospital.service.PatientService;
import com.faifly.hospital.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
@RequiredArgsConstructor
@Slf4j
public class HospitalController {

    private final VisitService visitService;
    private final PatientService patientService;

    @GetMapping("/getPatients")
    public ResponseEntity<ResponsePatientDto> getListPatients(@RequestParam(required = false, defaultValue = "1") Long page,
                                                              @RequestParam(required = false, defaultValue = "20") Long size,
                                                              @RequestParam(required = false) String search,
                                                              @RequestParam(required = false) List<Long> doctorIds) {
        ResponsePatientDto responsePatientDto = null;
        if (search == null && doctorIds == null) {
            responsePatientDto = patientService.getAllPatients();
        } else {
            patientService.getPatientsByFilter();
        }
        return ResponseEntity.ok(responsePatientDto);
    }

    @PostMapping("/visit")
    public ResponseEntity<RequestVisitDto> createVisit(@RequestBody RequestVisitDto visitDto) {
        visitService.createVisit(visitDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(visitDto);
    }


}
