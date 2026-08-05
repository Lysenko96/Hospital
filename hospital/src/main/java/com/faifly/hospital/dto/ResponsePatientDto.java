package com.faifly.hospital.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ResponsePatientDto {

    private List<PatientData> data;
    private int count;
}
