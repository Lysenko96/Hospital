package com.faifly.hospital.inegration.visit;

import com.faifly.hospital.controller.VisitException;
import com.faifly.hospital.dto.RequestVisitDto;
import com.faifly.hospital.model.Visit;
import com.faifly.hospital.repository.DoctorRepository;
import com.faifly.hospital.repository.PatientRepository;
import com.faifly.hospital.repository.VisitRepository;
import com.faifly.hospital.service.VisitService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class VisitTest {

    @Autowired
    private VisitService visitService;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private VisitRepository visitRepository;

    @Test
    void whenCreateVisitThenExists() {
        var doctorActual = doctorRepository.findById(1L);
        Assertions.assertNotNull(doctorActual);
        Assertions.assertTrue(doctorActual.isPresent());
        var patientActual = patientRepository.findById(1L);
        Assertions.assertNotNull(patientActual);
        Assertions.assertTrue(patientActual.isPresent());
        Assertions.assertTrue(doctorActual.get().getPatients().contains(patientActual.get()));
        Assertions.assertTrue(patientActual.get().getDoctors().contains(doctorActual.get()));
        visitService.createVisit(RequestVisitDto.builder()
                .start("2026-08-04 10:00:00 -0400")
                .end("2026-08-04 12:00:00 -0400")
                .doctorId(1L)
                .patientId(1L)
                .build());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
        var visitList = visitRepository.findByStartDateTimeAndEndDateTimeBetween(
                OffsetDateTime.parse("2026-08-04 10:00:00 -0400", formatter),
                OffsetDateTime.parse("2026-08-04 12:00:00 -0400", formatter));
        System.out.println(visitList);
        Assertions.assertTrue(visitList.contains(Visit.builder()
                .id(5L)
                .startDateTime(OffsetDateTime.parse("2026-08-04 10:00:00 -0400", formatter))
                .endDateTime(OffsetDateTime.parse("2026-08-04 12:00:00 -0400", formatter))
                .doctor(doctorActual.get())
                .patient(patientActual.get())
                .build()));
    }

    @Test
    void whenVisitExistsThenThrow() {
        var doctorActual = doctorRepository.findById(1L);
        Assertions.assertNotNull(doctorActual);
        Assertions.assertTrue(doctorActual.isPresent());
        var patientActual = patientRepository.findById(1L);
        Assertions.assertNotNull(patientActual);
        Assertions.assertTrue(patientActual.isPresent());
        Assertions.assertTrue(doctorActual.get().getPatients().contains(patientActual.get()));
        Assertions.assertTrue(patientActual.get().getDoctors().contains(doctorActual.get()));
        visitService.createVisit(RequestVisitDto.builder()
                .start("2026-08-04 10:00:00 -0400")
                .end("2026-08-04 12:00:00 -0400")
                .doctorId(1L)
                .patientId(1L)
                .build());
        Assertions.assertThrows(VisitException.class, () -> visitService.createVisit(RequestVisitDto.builder()
                .start("2026-08-04 10:00:00 -0400")
                .end("2026-08-04 12:00:00 -0400")
                .doctorId(1L)
                .patientId(1L)
                .build()));
    }

    @Test
    void whenDoctorNotExistsThenThrow() {
        Assertions.assertThrows(NoSuchElementException.class, () -> visitService.createVisit(RequestVisitDto.builder()
                .start("2026-08-04 10:00:00 -0400")
                .end("2026-08-04 12:00:00 -0400")
                .doctorId(6L)
                .patientId(1L)
                .build()));
    }

    @Test
    void whenPatientNotExistsThenThrow() {
        Assertions.assertThrows(NoSuchElementException.class, () -> visitService.createVisit(RequestVisitDto.builder()
                .start("2026-08-04 10:00:00 -0400")
                .end("2026-08-04 12:00:00 -0400")
                .doctorId(1L)
                .patientId(6L)
                .build()));
    }


}
