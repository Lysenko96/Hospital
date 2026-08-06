package com.faifly.hospital.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DoctorDto {

    private String firstName;
    private String lastName;
    private long totalPatients;
}
