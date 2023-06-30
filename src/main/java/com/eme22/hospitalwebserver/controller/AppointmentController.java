package com.eme22.hospitalwebserver.controller;

import com.eme22.hospitalwebserver.model.Appointment;
import com.eme22.hospitalwebserver.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/search/findByPatientByPatientId_DniAndFinished")
    public ResponseEntity<List<Appointment>> getFinishedAppointments(@RequestParam(name = "dni") long id) {
        List<Appointment> appointments = appointmentService.getFinishedAppointmentByDni(id);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/search/findByPatientByPatientId_DniAndNotFinished")
    public ResponseEntity<List<Appointment>> getUnFinishedAppointments(@RequestParam(name = "dni") long id) {
        List<Appointment> appointments = appointmentService.getUnFinishedAppointmentByDni(id);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/search/findByMedicByMedicId_Id")
    public ResponseEntity<List<Appointment>> getMedicAppointmentsById(@RequestParam(name = "id") long id) {
        List<Appointment> appointments = appointmentService.getAppointmentsByMedicId(id);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/search/findByMedicByMedicId_Dni")
    public ResponseEntity<List<Appointment>> getMedicAppointmentsByDni(@RequestParam(name = "dni") long id) {
        List<Appointment> appointments = appointmentService.getAppointmentsByMedicDni(id);
        return ResponseEntity.ok(appointments);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable long id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        return appointment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> saveAppointment(@RequestBody Appointment appointment) {
        try  {
            Appointment savedAppointment = appointmentService.saveAppointment(appointment);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAppointment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}