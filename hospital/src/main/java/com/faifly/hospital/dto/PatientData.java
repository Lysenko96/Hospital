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
public class PatientData {

    private String firstName;
    private String lastName;
    @Builder.Default
    private List<VisitDto> lastVisits = new ArrayList<>();
}
