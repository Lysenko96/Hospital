package com.faifly.hospital.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class DoctorDto {

    private String firstName;
    private String lastName;
    private int totalPatients;
}
