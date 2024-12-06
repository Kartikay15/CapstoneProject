package com.springboot.capstone_app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.capstone_app.model.Kpi;
import com.springboot.capstone_app.service.KpiService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/kpis")
public class KpiController {

    @Autowired
    private KpiService kpiService;

    // Create KPI
    @PostMapping("/")
    public ResponseEntity<Kpi> createKpi(@RequestBody Kpi kpi) {
        Kpi createdKpi = kpiService.createKpi(kpi);
        return new ResponseEntity<>(createdKpi, HttpStatus.CREATED);
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Kpi>> getAllKpis() {
        List<Kpi> kpis = kpiService.getAllKpis();
        return new ResponseEntity<>(kpis, HttpStatus.OK);
    }

    // Modify KPI value
    @PatchMapping("/{kpiID}")
    public ResponseEntity<Kpi> modifyKpiValue(@PathVariable int kpiID, @RequestParam int value) {
        Kpi updatedKpi = kpiService.modifyKpiValue(kpiID, value);
        return new ResponseEntity<>(updatedKpi, HttpStatus.OK);
    }

    // Get KPI by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Optional> getKpiByName(@PathVariable String name) {
        Optional<Kpi> kpi = kpiService.getKpiByName(name);
        return new ResponseEntity<>(kpi, HttpStatus.OK);
    }

    // Get KPI by ID
    @GetMapping("/{kpiID}")
    public ResponseEntity<Kpi> getKpiById(@PathVariable int kpiID) {
        Kpi kpi = kpiService.getKpiById(kpiID);
        return new ResponseEntity<>(kpi, HttpStatus.OK);
    }
    
    @PatchMapping("/modify/{name}")
    public ResponseEntity<Kpi> modifyKpiValueByName(
            @PathVariable String name,
            @RequestParam int newValue) {
        Kpi updatedKpi = kpiService.modifyKpiByName(name, newValue);
        return new ResponseEntity<>(updatedKpi, HttpStatus.OK);
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<String> refreshKpis() {
        kpiService.refreshKpis();
        return new ResponseEntity<>("KPIs updated successfully", HttpStatus.OK);
    }
}
