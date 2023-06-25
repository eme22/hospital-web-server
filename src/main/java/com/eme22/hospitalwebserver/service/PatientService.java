package com.eme22.hospitalwebserver.service;

import com.eme22.hospitalwebserver.model.Patient;
import com.eme22.hospitalwebserver.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(long id) {
        return patientRepository.findById(id);
    }

    public Optional<Patient> getPatientByDni(long dni) {
        return patientRepository.findByDni(dni);
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(long id) {
        patientRepository.deleteById(id);
    }
}