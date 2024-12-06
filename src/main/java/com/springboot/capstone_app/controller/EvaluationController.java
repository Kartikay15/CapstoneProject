package com.springboot.capstone_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.capstone_app.enums.EvalStatus;
import com.springboot.capstone_app.enums.EvalVerdict;
import com.springboot.capstone_app.model.Evaluation;
import com.springboot.capstone_app.service.EvaluationService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    // Create Evaluation for a given Car ID
    @PostMapping("/car/{carId}")
    public ResponseEntity<Evaluation> createEvaluation(@PathVariable int carId) {
        Evaluation evaluation = evaluationService.createEvaluation(carId);
        return new ResponseEntity<>(evaluation, HttpStatus.CREATED);
    }

    // Update Evaluation details
    @PatchMapping("/{evaluationId}")
    public ResponseEntity<Evaluation> updateEvaluation(
            @PathVariable int evaluationId,
            @RequestParam(required = false) EvalStatus evalStatus,
            @RequestParam(required = false) EvalVerdict evalVerdict,
            @RequestParam(required = false) Integer valuation) {
    	
        Evaluation updatedEvaluation = evaluationService.updateEvaluation(evaluationId, evalStatus, evalVerdict, valuation);
        return new ResponseEntity<>(updatedEvaluation, HttpStatus.OK);
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Evaluation>> getAllEvaluations() {
        List<Evaluation> evaluations = evaluationService.getAllEvaluations();
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }
    
    @PatchMapping("/status/{evaluationId}")
    public ResponseEntity<Evaluation> alterEvalStatus(
            @PathVariable int evaluationId,
            @RequestParam EvalStatus evalStatus) {
        
        Evaluation updatedEvaluation = evaluationService.updateEvalStatus(evaluationId, evalStatus);
        return new ResponseEntity<>(updatedEvaluation, HttpStatus.OK);
    }
    
 // Alter evalVerdict for a given Evaluation ID
    @PatchMapping("/verdict/{evaluationId}")
    public ResponseEntity<Evaluation> alterEvalVerdict(
            @PathVariable int evaluationId,
            @RequestParam EvalVerdict evalVerdict) {
        
        Evaluation updatedEvaluation = evaluationService.updateEvalVerdict(evaluationId, evalVerdict);
        return new ResponseEntity<>(updatedEvaluation, HttpStatus.OK);
    }
    
    @PatchMapping("/updateStatusVerdict/{evaluationId}")
    public ResponseEntity<Evaluation> updateStatusAndVerdict(
            @PathVariable int evaluationId,
            @RequestParam EvalStatus evalStatus,
            @RequestParam EvalVerdict evalVerdict) {
        
        // Call the new service method to update both evalStatus and evalVerdict
        Evaluation updatedEvaluation = evaluationService.updateEvalStatusAndVerdict(evaluationId, evalStatus, evalVerdict);
        return new ResponseEntity<>(updatedEvaluation, HttpStatus.OK);
    }

}
