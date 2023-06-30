package com.eme22.hospitalwebserver.service;

import com.eme22.hospitalwebserver.model.Appointment;
import com.eme22.hospitalwebserver.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(long id) {
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> getFinishedAppointmentByDni(long dni) {
        return appointmentRepository.findByPatientByPatientId_DniAndFinished(dni, true);
    }

    public List<Appointment> getUnFinishedAppointmentByDni(long dni) {
        return appointmentRepository.findByPatientByPatientId_DniAndFinished(dni, false);
    }

    public List<Appointment> getAppointmentsByMedicDni(long dni) {
        return appointmentRepository.findByMedicByMedicId_Dni(dni);
    }

    public List<Appointment> getAppointmentsByMedicId(long dni) {
        return appointmentRepository.findByMedicByMedicId_Id(dni);
    }
}