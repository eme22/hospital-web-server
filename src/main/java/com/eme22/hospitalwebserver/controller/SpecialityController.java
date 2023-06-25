package com.eme22.hospitalwebserver.controller;

import com.eme22.hospitalwebserver.model.Speciality;
import com.eme22.hospitalwebserver.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/speciality")
public class SpecialityController {
    private final SpecialityService specialityService;

    @Autowired
    public SpecialityController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @GetMapping
    public ResponseEntity<List<Speciality>> getAllSpecialities() {
        List<Speciality> specialities = specialityService.getAllSpecialities();
        return ResponseEntity.ok(specialities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Speciality> getSpecialityById(@PathVariable long id) {
        Optional<Speciality> speciality = specialityService.getSpecialityById(id);
        return speciality.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Speciality> saveSpeciality(@RequestBody Speciality speciality) {
        Speciality savedSpeciality = specialityService.saveSpeciality(speciality);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSpeciality);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpeciality(@PathVariable long id) {
        specialityService.deleteSpeciality(id);
        return ResponseEntity.noContent().build();
    }
}