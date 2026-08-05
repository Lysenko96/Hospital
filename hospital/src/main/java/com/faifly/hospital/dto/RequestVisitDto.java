package com.faifly.hospital.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestVisitDto {

    private String start;
    private String end;
    private Long patientId;
    private Long doctorId;
}
