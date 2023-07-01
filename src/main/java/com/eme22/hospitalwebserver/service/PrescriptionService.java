package com.eme22.hospitalwebserver.service;

import com.eme22.hospitalwebserver.model.Prescription;
import com.eme22.hospitalwebserver.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Optional<Prescription> getPrescriptionById(long id) {
        return prescriptionRepository.findById(id);
    }

    public List<Prescription> getPrescriptionByAppointmentId(long id) {
        return prescriptionRepository.findByAppointmentByAppointmentId_Id(id);
    }



    public Prescription savePrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public void deletePrescription(long id) {
        prescriptionRepository.deleteById(id);
    }
}