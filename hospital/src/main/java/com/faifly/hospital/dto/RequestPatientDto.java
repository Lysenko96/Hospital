package com.faifly.hospital.dto;

import java.util.ArrayList;
import java.util.List;

public class RequestPatientDto {

    private Long page;
    private Long size;
    private String search;
    private List<Long> doctorIds = new ArrayList<>();
}
