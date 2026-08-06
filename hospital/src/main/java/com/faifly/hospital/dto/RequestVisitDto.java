package com.faifly.hospital.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestVisitDto {

    private String start;
    private String end;
    private Long patientId;
    private Long doctorId;
}
