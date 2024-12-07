package com.springboot.capstone_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.springboot.capstone_app.model.Kpi;

import com.springboot.capstone_app.repository.KpiRepository;

@Service
public class KpiService {

    @Autowired
    private KpiRepository kpiRepository;


    // Create KPI
    public Kpi createKpi(Kpi kpi) {
        return kpiRepository.save(kpi);
    }

    // Modify KPI value
    public Kpi modifyKpiValue(int kpiID, int newValue) {
        Kpi kpi = kpiRepository.findById(kpiID)
                .orElseThrow(() -> new IllegalArgumentException("KPI not found"));
        kpi.setValue(newValue);
        return kpiRepository.save(kpi);
    }
    
    public List<Kpi> getAllKpis() {
    	refreshKpis();
        return kpiRepository.findAll();
    }

    // Get KPI by name
    public Optional<Kpi> getKpiByName(String name) {
        return kpiRepository.findByName(name);
    }

    // Get KPI by ID
    public Kpi getKpiById(int kpiID) {
        return kpiRepository.findById(kpiID)
                .orElseThrow(() -> new IllegalArgumentException("KPI not found"));
    }
    
    public Kpi modifyKpiByName(String name, int newValue) {
        Kpi kpi = kpiRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("KPI with name " + name + " not found"));
        kpi.setValue(newValue);
        return kpiRepository.save(kpi);
    }
    
    public void refreshKpis() {
        int revResell = kpiRepository.getRevResell();
        int revRental = kpiRepository.getRevRental();
        int cancelBookings = kpiRepository.getCancelBookings();
        int hiredBookings = kpiRepository.getHiredBookings();
        int totalRentals = kpiRepository.getTotalRentals();
        int carRentedOut = hiredBookings;
        int carsSold = kpiRepository.getCarsSold();
        int users = kpiRepository.getUsers();
        int rentalInventory = totalRentals-carRentedOut;
        int resellInventory = kpiRepository.getResellInventory();
        int listedCars = kpiRepository.getListedCars();

        modifyKpiByName("revResell", revResell);
        modifyKpiByName("revRental", revRental);
        modifyKpiByName("cancelBookings", cancelBookings);
        modifyKpiByName("hiredBookings", hiredBookings);
        modifyKpiByName("totalRentals",totalRentals);
        modifyKpiByName("carRentedOut",carRentedOut);
        modifyKpiByName("carsSold",carsSold);
        modifyKpiByName("users",users);
        modifyKpiByName("rentalInventory",rentalInventory);
        modifyKpiByName("resellInventory", resellInventory);
        modifyKpiByName("listedCars",listedCars);
    }
}
