package com.eme22.hospitalwebserver.controller;

import com.eme22.hospitalwebserver.model.Medication;
import com.eme22.hospitalwebserver.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medication")
public class MedicationController {
    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping
    public ResponseEntity<List<Medication>> getAllMedications() {
        List<Medication> medications = medicationService.getAllMedications();
        return ResponseEntity.ok(medications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medication> getMedicationById(@PathVariable long id) {
        Optional<Medication> medication = medicationService.getMedicationById(id);
        return medication.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Medication> saveMedication(@RequestBody Medication medication) {
        Medication savedMedication = medicationService.saveMedication(medication);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMedication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable long id) {
        medicationService.deleteMedication(id);
        return ResponseEntity.noContent().build();
    }
}