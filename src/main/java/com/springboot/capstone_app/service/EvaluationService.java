package com.springboot.capstone_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.capstone_app.enums.EvalStatus;
import com.springboot.capstone_app.enums.EvalVerdict;
import com.springboot.capstone_app.model.Car;
import com.springboot.capstone_app.model.Evaluation;
import com.springboot.capstone_app.repository.CarRepository;
import com.springboot.capstone_app.repository.EvaluationRepository;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private CarRepository carRepository;

    // Create Evaluation given Car ID
    public Evaluation createEvaluation(int carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new IllegalArgumentException("Car not found"));
        
        String model = car.getCar_model();
        int year = Integer.parseInt(car.getPurchase_year());
        String fuelType = car.getFuel_type(), bodyType = car.getBody_type(), condition = car.getCar_condition();
        int calculatedPrice = calculateCarValuation(model,year,fuelType,bodyType,condition,2024);
        car.setPrice(calculatedPrice);
        carRepository.save(car);
        
        Evaluation evaluation = new Evaluation();
        evaluation.setCar(car);
        evaluation.setEvalstatus(EvalStatus.PENDING); // Default status
        evaluation.setValuation(calculatedPrice);
        return evaluationRepository.save(evaluation);
    }
    
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll(); // Returns all evaluations
    }

    // Update Evaluation details
    public Evaluation updateEvaluation(int evaluationId, EvalStatus evalStatus, EvalVerdict evalVerdict, int valuation) {
        Evaluation evaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new IllegalArgumentException("Evaluation not found"));
        if (evalStatus != null) {
            evaluation.setEvalstatus(evalStatus);
        }
        if (evalVerdict != null) {
            evaluation.setEvalVerdict(evalVerdict);
        }
        evaluation.setValuation(valuation);
        return evaluationRepository.save(evaluation);
    }
    
 // Service method to update evalStatus
    public Evaluation updateEvalStatus(int evaluationId, EvalStatus evalStatus) {
        Evaluation evaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new IllegalArgumentException("Evaluation not found"));
        
        evaluation.setEvalstatus(evalStatus);
        
        return evaluationRepository.save(evaluation);
    }
    
 // Service method to update evalVerdict
    public Evaluation updateEvalVerdict(int evaluationId, EvalVerdict evalVerdict) {
        Evaluation evaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new IllegalArgumentException("Evaluation not found"));
        
        evaluation.setEvalVerdict(evalVerdict); // Set the new evalVerdict
        
        return evaluationRepository.save(evaluation);
    }


    
    // Method to calculate the car valuation
    public int calculateCarValuation(String model, int year, String fuelType, String bodyType, String condition, int currentYear) {
        // Base prices for different car models
        double basePrice = getBasePrice(model);
        
        // Fuel type multipliers
        double fuelMultiplier = getFuelMultiplier(fuelType);
        
        // Condition multipliers
        double conditionMultiplier = getConditionMultiplier(condition);
        
        // Body type multipliers
        double bodyMultiplier = getBodyMultiplier(bodyType);
        
        // Calculate the age of the car
        int age = currentYear - year;
        
        // Calculate depreciation factor (Assuming 5% depreciation per year)
        double depreciationFactor = 1 - (0.05 * age);
        if (depreciationFactor < 0.1) {
            depreciationFactor = 0.1; // Ensure a minimum of 10% value
        }
        
        // Final valuation
        double valuation = basePrice * depreciationFactor * fuelMultiplier * conditionMultiplier * bodyMultiplier;
        
        return (int)valuation;
    }
    
    // Get base price based on car model
    private static double getBasePrice(String model) {
        switch (model) {
            case "SUV":
                return 1000000;
            case "SEDAN":
                return 700000;
            case "HATCHBACK":
                return 400000;
            default:
                return 500000; // Default base price if model not found
        }
    }
    
    // Get fuel type multiplier
    private static double getFuelMultiplier(String fuelType) {
        switch (fuelType) {
            case "Petrol":
                return 1.0;
            case "Diesel":
                return 1.1;
            case "Electric":
                return 1.2;
            case "Hybrid":
                return 1.15;
            default:
                return 1.0; // Default multiplier for unknown fuel types
        }
    }
    
    // Get condition multiplier
    private static double getConditionMultiplier(String condition) {
        switch (condition) {
            case "Excellent":
                return 1.2;
            case "Good":
                return 1.0;
            case "Fair":
                return 0.8;
            case "Poor":
                return 0.5;
            default:
                return 1.0; // Default multiplier for unknown conditions
        }
    }
    
    // Get body type multiplier
    private static double getBodyMultiplier(String bodyType) {
        switch (bodyType) {
            case "Metal":
                return 1.0;
            case "Composite":
                return 1.1;
            case "Fiber":
                return 0.9;
            default:
                return 1.0; // Default multiplier for unknown body types
        }
    }
}
