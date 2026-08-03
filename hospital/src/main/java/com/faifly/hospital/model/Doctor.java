package com.faifly.hospital.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private TimeZone timezone;
    @ToString.Exclude
    @ManyToMany()
    @JoinTable(name = "visit",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private List<Patient> patients = new ArrayList<>();
    @OneToMany(mappedBy = "doctor")
    private List<Visit> visits = new ArrayList<>();


    public void addVisit(Visit visit) {
        visit.setDoctor(this);
        visits.add(visit);
    }
}
