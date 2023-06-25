package com.eme22.hospitalwebserver.controller;

import com.eme22.hospitalwebserver.model.Area;
import com.eme22.hospitalwebserver.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/area")
public class AreaController {
    private final AreaService areaService;

    @Autowired
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping
    public ResponseEntity<List<Area>> getAllAreas() {
        List<Area> areas = areaService.getAllAreas();
        return ResponseEntity.ok(areas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Area> getAreaById(@PathVariable long id) {
        Optional<Area> area = areaService.getAreaById(id);
        return area.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Area> saveArea(@RequestBody Area area) {
        Area savedArea = areaService.saveArea(area);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArea);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArea(@PathVariable long id) {
        areaService.deleteArea(id);
        return ResponseEntity.noContent().build();
    }
}