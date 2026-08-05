package com.faifly.hospital.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "visit")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private OffsetDateTime startDateTime;
    private OffsetDateTime endDateTime;
    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false, foreignKey = @ForeignKey(name = "fk_patient_visit"))
    private Patient patient;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false, foreignKey = @ForeignKey(name = "fk_doctor_visit"))
    private Doctor doctor;

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        this.doctor.getVisits().add(this);
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        this.patient.getVisits().add(this);
    }
}
