package com.faifly.hospital.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @ToString.Exclude
    @Builder.Default
    @ManyToMany(mappedBy = "patients")
    private List<Doctor> doctors = new ArrayList<>();
    @OneToMany(mappedBy = "patient")
    @Builder.Default
    private List<Visit> visits = new ArrayList<>();
}
