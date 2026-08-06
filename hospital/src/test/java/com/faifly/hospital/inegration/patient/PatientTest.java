package com.faifly.hospital.inegration.patient;

import com.faifly.hospital.HospitalApplication;
import com.faifly.hospital.model.Doctor;
import com.faifly.hospital.model.Patient;
import com.faifly.hospital.model.Visit;
import com.faifly.hospital.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.*;

@SpringBootTest(classes = HospitalApplication.class)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PatientTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void whenFindByNamePatientThenExists() {
        var doctor1 = Doctor.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .timezone(TimeZone.getTimeZone("Europe/Kiev"))
                .build();
        var doctor2 = Doctor.builder()
                .id(2L)
                .firstName("Ai")
                .lastName("Bolit")
                .timezone(TimeZone.getTimeZone("America/New_York"))
                .build();
        var visit1 = Visit.builder()
                .id(1L)
                .startDateTime(OffsetDateTime.parse("2026-08-04T00:00Z"))
                .endDateTime(OffsetDateTime.parse("2026-08-04T02:00Z"))
                .doctor(doctor1)
                .build();
        var visit2 = Visit.builder()
                .id(2L)
                .startDateTime(OffsetDateTime.parse("2026-08-04T07:00Z"))
                .endDateTime(OffsetDateTime.parse("2026-08-04T09:00Z"))
                .doctor(doctor2)
                .build();
        var patient = Patient.builder()
                .id(1L)
                .firstName("Patient0")
                .lastName("Zero")
                .doctors(new ArrayList<>(Arrays.asList(doctor1, doctor2)))
                .build();
        patient.getDoctors().get(0).setPatients(Arrays.asList(patient));
        patient.getDoctors().get(1).setPatients(Arrays.asList(patient));

        visit1.setPatient(patient);
        visit2.setPatient(patient);

        patient.setVisits(new ArrayList<>(Arrays.asList(visit1, visit2)));

        List<Patient> patientList = patientRepository.findByName("Patient0");

        List<Doctor> doctors = patient.getDoctors();
        Patient patientActual = patientList.get(0);

        Assertions.assertEquals(patient.getId(), patientActual.getId());
        Assertions.assertEquals(patient.getFirstName(), patientActual.getFirstName());
        Assertions.assertEquals(patient.getLastName(), patientActual.getLastName());

        Assertions.assertEquals(doctors.get(0).getId(), patientActual.getDoctors().get(0).getId());
        Assertions.assertEquals(doctors.get(0).getFirstName(), patientActual.getDoctors().get(0).getFirstName());
        Assertions.assertEquals(doctors.get(0).getLastName(), patientActual.getDoctors().get(0).getLastName());
        Assertions.assertEquals(doctors.get(0).getTimezone(), patientActual.getDoctors().get(0).getTimezone());

        Assertions.assertEquals(doctors.get(1).getId(), patientActual.getDoctors().get(1).getId());
        Assertions.assertEquals(doctors.get(1).getFirstName(), patientActual.getDoctors().get(1).getFirstName());
        Assertions.assertEquals(doctors.get(1).getLastName(), patientActual.getDoctors().get(1).getLastName());
        Assertions.assertEquals(doctors.get(1).getTimezone(), patientActual.getDoctors().get(1).getTimezone());

        Assertions.assertEquals(doctors.size(), patientActual.getDoctors().size());

        Assertions.assertEquals(patient.getVisits().get(0).getId(), patientList.get(0).getVisits().get(0).getId());
        Assertions.assertEquals(patient.getVisits().get(0).getStartDateTime(), patientList.get(0).getVisits().get(0).getStartDateTime());
        Assertions.assertEquals(patient.getVisits().get(0).getEndDateTime(), patientList.get(0).getVisits().get(0).getEndDateTime());
        Assertions.assertEquals(patient.getVisits().get(0).getDoctor().getId(), patientList.get(0).getVisits().get(0).getDoctor().getId());
        Assertions.assertEquals(patient.getVisits().get(0).getPatient().getId(), patientList.get(0).getVisits().get(0).getPatient().getId());

        Assertions.assertEquals(patient.getVisits().size(), patientActual.getVisits().size());

    }

    @Test
    void whenFindByDoctorIdsThenExists() {
        var doctor1 = Doctor.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .timezone(TimeZone.getTimeZone("Europe/Kiev"))
                .build();
        var doctor2 = Doctor.builder()
                .id(2L)
                .firstName("Ai")
                .lastName("Bolit")
                .timezone(TimeZone.getTimeZone("America/New_York"))
                .build();
        var visit1 = Visit.builder()
                .id(1L)
                .startDateTime(OffsetDateTime.parse("2026-08-04T00:00Z"))
                .endDateTime(OffsetDateTime.parse("2026-08-04T02:00Z"))
                .doctor(doctor1)
                .build();
        var patient = Patient.builder()
                .id(1L)
                .firstName("Patient0")
                .lastName("Zero")
                .doctors(new ArrayList<>(Arrays.asList(doctor1, doctor2)))
                .build();
        patient.getDoctors().get(0).setPatients(Arrays.asList(patient));
        patient.getDoctors().get(1).setPatients(Arrays.asList(patient));

        visit1.setPatient(patient);

        patient.setVisits(new ArrayList<>(Arrays.asList(visit1)));

        List<Patient> patientList = patientRepository.findByDoctorIds(Arrays.asList(1L));

        List<Doctor> doctors = patient.getDoctors();
        Patient patientActual = patientList.get(0);

        Assertions.assertEquals(patient.getId(), patientActual.getId());
        Assertions.assertEquals(patient.getFirstName(), patientActual.getFirstName());
        Assertions.assertEquals(patient.getLastName(), patientActual.getLastName());

        Assertions.assertEquals(doctors.get(0).getId(), patientActual.getDoctors().get(0).getId());
        Assertions.assertEquals(doctors.get(0).getFirstName(), patientActual.getDoctors().get(0).getFirstName());
        Assertions.assertEquals(doctors.get(0).getLastName(), patientActual.getDoctors().get(0).getLastName());
        Assertions.assertEquals(doctors.get(0).getTimezone(), patientActual.getDoctors().get(0).getTimezone());

        Assertions.assertEquals(doctors.get(1).getId(), patientActual.getDoctors().get(1).getId());
        Assertions.assertEquals(doctors.get(1).getFirstName(), patientActual.getDoctors().get(1).getFirstName());
        Assertions.assertEquals(doctors.get(1).getLastName(), patientActual.getDoctors().get(1).getLastName());
        Assertions.assertEquals(doctors.get(1).getTimezone(), patientActual.getDoctors().get(1).getTimezone());

        Assertions.assertEquals(doctors.size(), patientActual.getDoctors().size());

        Assertions.assertEquals(patient.getVisits().get(0).getId(), patientList.get(0).getVisits().get(0).getId());
        Assertions.assertEquals(patient.getVisits().get(0).getStartDateTime(), patientList.get(0).getVisits().get(0).getStartDateTime());
        Assertions.assertEquals(patient.getVisits().get(0).getEndDateTime(), patientList.get(0).getVisits().get(0).getEndDateTime());
        Assertions.assertEquals(patient.getVisits().get(0).getDoctor().getId(), patientList.get(0).getVisits().get(0).getDoctor().getId());
        Assertions.assertEquals(patient.getVisits().get(0).getPatient().getId(), patientList.get(0).getVisits().get(0).getPatient().getId());

        Assertions.assertEquals(patient.getVisits().size(), patientActual.getVisits().size());
    }

    @Test
    void whenFindByFirstNameAndDoctorIdsThenExists() {
        var doctor1 = Doctor.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .timezone(TimeZone.getTimeZone("Europe/Kiev"))
                .build();
        var doctor2 = Doctor.builder()
                .id(2L)
                .firstName("Ai")
                .lastName("Bolit")
                .timezone(TimeZone.getTimeZone("America/New_York"))
                .build();
        var visit1 = Visit.builder()
                .id(1L)
                .startDateTime(OffsetDateTime.parse("2026-08-04T00:00Z"))
                .endDateTime(OffsetDateTime.parse("2026-08-04T02:00Z"))
                .doctor(doctor1)
                .build();
        var visit2 = Visit.builder()
                .id(2L)
                .startDateTime(OffsetDateTime.parse("2026-08-04T07:00Z"))
                .endDateTime(OffsetDateTime.parse("2026-08-04T09:00Z"))
                .doctor(doctor2)
                .build();
        var patient = Patient.builder()
                .id(1L)
                .firstName("Patient0")
                .lastName("Zero")
                .doctors(new ArrayList<>(Arrays.asList(doctor1, doctor2)))
                .build();
        patient.getDoctors().get(0).setPatients(Arrays.asList(patient));
        patient.getDoctors().get(1).setPatients(Arrays.asList(patient));

        visit1.setPatient(patient);
        visit2.setPatient(patient);

        patient.setVisits(new ArrayList<>(Arrays.asList(visit1, visit2)));

        var patient2 = Patient.builder()
                .id(2L)
                .firstName("Patient1")
                .lastName("One")
                .doctors(new ArrayList<>(Arrays.asList(doctor2)))
                .build();

        patient2.getDoctors().get(0).setPatients(Arrays.asList(patient2));

        var visit3 = Visit.builder()
                .id(3L)
                .startDateTime(OffsetDateTime.parse("2026-08-04T09:00Z"))
                .endDateTime(OffsetDateTime.parse("2026-08-04T11:00Z"))
                .doctor(doctor2)
                .build();

        var visit4 = Visit.builder()
                .id(4L)
                .startDateTime(OffsetDateTime.parse("2026-08-04T11:00Z"))
                .endDateTime(OffsetDateTime.parse("2026-08-04T13:00Z"))
                .doctor(doctor2)
                .build();

        visit3.setPatient(patient2);
        visit4.setPatient(patient2);

        patient2.setVisits(new ArrayList<>(Arrays.asList(visit3, visit4)));


        List<Patient> patientList = patientRepository.findByNameAndDoctorIds("Patient0", Arrays.asList(2L));

        List<Doctor> doctors = patient.getDoctors();
        Patient patientActual = patientList.get(0);

        Assertions.assertEquals(patient.getId(), patientActual.getId());
        Assertions.assertEquals(patient.getFirstName(), patientActual.getFirstName());
        Assertions.assertEquals(patient.getLastName(), patientActual.getLastName());

        Assertions.assertEquals(doctors.get(0).getId(), patientActual.getDoctors().get(0).getId());
        Assertions.assertEquals(doctors.get(0).getFirstName(), patientActual.getDoctors().get(0).getFirstName());
        Assertions.assertEquals(doctors.get(0).getLastName(), patientActual.getDoctors().get(0).getLastName());
        Assertions.assertEquals(doctors.get(0).getTimezone(), patientActual.getDoctors().get(0).getTimezone());

        Assertions.assertEquals(doctors.get(1).getId(), patientActual.getDoctors().get(1).getId());
        Assertions.assertEquals(doctors.get(1).getFirstName(), patientActual.getDoctors().get(1).getFirstName());
        Assertions.assertEquals(doctors.get(1).getLastName(), patientActual.getDoctors().get(1).getLastName());
        Assertions.assertEquals(doctors.get(1).getTimezone(), patientActual.getDoctors().get(1).getTimezone());

        Assertions.assertEquals(doctors.size(), patientActual.getDoctors().size());

        Assertions.assertEquals(patient.getVisits().get(0).getId(), patientActual.getVisits().get(0).getId());
        Assertions.assertEquals(patient.getVisits().get(0).getStartDateTime(), patientActual.getVisits().get(0).getStartDateTime());
        Assertions.assertEquals(patient.getVisits().get(0).getEndDateTime(), patientActual.getVisits().get(0).getEndDateTime());
        Assertions.assertEquals(patient.getVisits().get(0).getDoctor().getId(), patientActual.getVisits().get(0).getDoctor().getId());
        Assertions.assertEquals(patient.getVisits().get(0).getPatient().getId(), patientActual.getVisits().get(0).getPatient().getId());

        Assertions.assertEquals(patient.getVisits().size(), patientActual.getVisits().size());

        List<Doctor> doctors2 = patient2.getDoctors();
        Patient patientActual2 = patientList.get(1);

        Assertions.assertEquals(patient2.getId(), patientActual2.getId());
        Assertions.assertEquals(patient2.getFirstName(), patientActual2.getFirstName());
        Assertions.assertEquals(patient2.getLastName(), patientActual2.getLastName());

        Assertions.assertEquals(doctors2.get(0).getId(), patientActual2.getDoctors().get(0).getId());
        Assertions.assertEquals(doctors2.get(0).getFirstName(), patientActual2.getDoctors().get(0).getFirstName());
        Assertions.assertEquals(doctors2.get(0).getLastName(), patientActual2.getDoctors().get(0).getLastName());
        Assertions.assertEquals(doctors2.get(0).getTimezone(), patientActual2.getDoctors().get(0).getTimezone());

        Assertions.assertEquals(doctors2.size(), new HashSet<>(patientActual2.getDoctors()).size());

        Assertions.assertEquals(patient2.getVisits().get(0).getId(), patientActual2.getVisits().get(0).getId());
        Assertions.assertEquals(patient2.getVisits().get(0).getStartDateTime(), patientActual2.getVisits().get(0).getStartDateTime());
        Assertions.assertEquals(patient2.getVisits().get(0).getEndDateTime(), patientActual2.getVisits().get(0).getEndDateTime());
        Assertions.assertEquals(patient2.getVisits().get(0).getDoctor().getId(), patientActual2.getVisits().get(0).getDoctor().getId());
        Assertions.assertEquals(patient2.getVisits().get(0).getPatient().getId(), patientActual2.getVisits().get(0).getPatient().getId());

        Assertions.assertEquals(patient2.getVisits().size(), patientActual2.getVisits().size());

    }

}
