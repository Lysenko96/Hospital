package com.faifly.hospital.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ResponsePatientDto {

    @Builder.Default
    private List<PatientData> data = new ArrayList<>();
    private int count;
}
