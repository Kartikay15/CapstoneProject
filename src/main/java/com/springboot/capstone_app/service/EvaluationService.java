package com.springboot.capstone_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.capstone_app.enums.CarStatus;
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
        int year = Integer.parseInt(car.getPurchase_year()), mileage = car.getMilage();
        String brand = car.getBrand(),fuelType = car.getFuel_type(), bodyType = car.getBody_type(), condition = car.getCar_condition();
        int calculatedPrice = calculateCarValuation(brand,model,year,fuelType,bodyType,condition,mileage,2024);
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

           
            if (evalVerdict == EvalVerdict.PASS) {
                Car car = evaluation.getCar(); 
                if (car != null) {
                    car.setCarStatus(CarStatus.INVENTORY);
                    carRepository.save(car); 
                }
            }
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
    public int calculateCarValuation(
            String brand, String model, int year, String fuelType, String bodyType, 
            String condition, int mileage, int currentYear) {

        
        double basePrice = getBasePrice(model);
        double brandMultiplier = getBrandMultiplier(brand);
        double fuelMultiplier = getFuelMultiplier(fuelType);
        double conditionMultiplier = getConditionMultiplier(condition);
        double bodyMultiplier = getBodyMultiplier(bodyType);
        double mileageMultiplier = getMileageMultiplier(mileage);
        int age = currentYear - year;
        double depreciationFactor = 1 - (0.05 * age);
        if (depreciationFactor < 0.1) {
            depreciationFactor = 0.1; // Ensure a minimum of 10% value
        }

        // Intermediate valuation before additional charges
        double valuation = basePrice * depreciationFactor * brandMultiplier 
                * fuelMultiplier * conditionMultiplier * bodyMultiplier * mileageMultiplier;

        // Add additional charges
        double gstRate = 0.18; // 18% GST
        double platformFeeRate = 0.02; // 2% platform fee
        double insuranceFee = 5000; // Flat fee for insurance
        double rtoFee = calculateRtoFee(basePrice); // Dynamic RTO fee

        valuation = valuation + (valuation * gstRate) + (valuation * platformFeeRate) + insuranceFee + rtoFee;

        return (int) valuation;
    }

    // Brand multiplier (Higher value for premium or popular brands)
    private static double getBrandMultiplier(String brand) {
        switch (brand.toLowerCase()) {
            case "toyota":
                return 1.1;
            case "honda":
                return 1.0;
            case "hyundai":
                return 0.9;
            case "maruti":
                return 0.8;
            case "tata":
                return 0.85;
            case "ford":
                return 0.9;
            case "bmw":
                return 1.3;
            case "mercedes":
                return 1.4;
            case "audi":
                return 1.35;
            case "volkswagen":
                return 1.2;
            default:
                return 1.0; // Default multiplier for unknown brands
        }
    }

    // RTO fee calculation based on base price
    private static double calculateRtoFee(double basePrice) {
        if (basePrice < 500000) {
            return 15000; // Low-cost cars
        } else if (basePrice < 1000000) {
            return 30000; // Mid-range cars
        } else {
            return 50000; // High-cost cars
        }
    }

    
    private static double getMileageMultiplier(int mileage) {
        if (mileage < 5000) {
            return 1.1; // Bonus for very low mileage
        } else if (mileage < 20000) {
            return 1.0; // Standard mileage
        } else if (mileage < 50000) {
            return 0.9; // Slightly reduced for moderate mileage
        } else if (mileage < 100000) {
            return 0.8; // Reduced for high mileage
        } else {
            return 0.6; // Major reduction for very high mileage
        }
    }

    // Base price (same as before)
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

    // Fuel type multiplier (same as before)
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

    // Condition multiplier (same as before)
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

    public Evaluation updateEvalStatusAndVerdict(int evaluationId, EvalStatus evalStatus, EvalVerdict evalVerdict) {
    	 // Find the Evaluation by ID
        Evaluation evaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new IllegalArgumentException("Evaluation not found"));

        // Retrieve the associated Car object from the Evaluation
        Car car = evaluation.getCar(); // Assuming there's a getCar() method in Evaluation

        // Update evalStatus if it's not null
        if (evalStatus != null) {
            evaluation.setEvalstatus(evalStatus);
        }

        // Update evalVerdict if it's not null
        if (evalVerdict != null) {
            evaluation.setEvalVerdict(evalVerdict);

            // If evalVerdict is PASS, update the car status to INVENTORY
            if (evalVerdict == EvalVerdict.PASS && car != null) {
                car.setCarStatus(CarStatus.INVENTORY); // Set car status to INVENTORY
                // Save the updated car object
                carRepository.save(car);
            }
        }

        // Save the updated evaluation object
        return evaluationRepository.save(evaluation);
    }
}
