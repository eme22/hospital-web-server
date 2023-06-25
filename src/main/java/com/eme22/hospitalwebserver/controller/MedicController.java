package com.eme22.hospitalwebserver.controller;

import com.eme22.hospitalwebserver.model.Medic;
import com.eme22.hospitalwebserver.service.MedicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medic")
public class MedicController {
    private final MedicService medicService;

    @Autowired
    public MedicController(MedicService medicService) {
        this.medicService = medicService;
    }

    @GetMapping
    public ResponseEntity<List<Medic>> getAllMedics() {
        List<Medic> medics = medicService.getAllMedics();
        return ResponseEntity.ok(medics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medic> getMedicById(@PathVariable long id) {
        Optional<Medic> medic = medicService.getMedicById(id);
        return medic.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/findBySpecialityBySpecId_Id")
    public ResponseEntity<List<Medic>> getMedicBySpecialityId(@RequestParam(name = "id") long id) {
        List<Medic> medics = medicService.getAllMedicsBySpeciality(id);
        return ResponseEntity.ok(medics);
    }

    @GetMapping("/search/findByHolidays_DateIsNotIn")
    public ResponseEntity<List<Medic>> getMedicByDay(@RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate date) {
        List<Medic> medics = medicService.getAllMedicsNotInDate(date);
        return ResponseEntity.ok(medics);
    }

    @PostMapping
    public ResponseEntity<Medic> saveMedic(@RequestBody Medic medic) {
        Medic savedMedic = medicService.saveMedic(medic);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMedic);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedic(@PathVariable long id) {
        medicService.deleteMedic(id);
        return ResponseEntity.noContent().build();
    }
}