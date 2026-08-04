package com.faifly.hospital.controller;

import com.faifly.hospital.dto.RequestVisitDto;
import com.faifly.hospital.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final VisitService visitService;

    @GetMapping("getPatients")
    public void getListPatients() {

    }

    @PostMapping("visit")
    public void createVisit(@RequestBody RequestVisitDto visitDto) {
        System.out.println(visitDto);
        visitService.save(visitDto);
    }


}
