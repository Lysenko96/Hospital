package com.faifly.hospital.controller;

import com.faifly.hospital.dto.RequestVisitDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital")
public class HospitalController {


    @GetMapping("getPatients")
    public void getListPatients() {

    }

    @PostMapping("visit")
    public void createVisit(@RequestBody RequestVisitDto visitDto) {
        System.out.println(visitDto);
    }


}
