package com.faifly.hospital.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class VisitDto {

    private String start;
    private String end;
    private DoctorDto doctor;
}
