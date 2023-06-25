package com.eme22.hospitalwebserver.service;

import com.eme22.hospitalwebserver.model.Medic;
import com.eme22.hospitalwebserver.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MedicService {
    private final MedicRepository medicRepository;

    @Autowired
    public MedicService(MedicRepository medicRepository) {
        this.medicRepository = medicRepository;
    }

    public List<Medic> getAllMedics() {
        return medicRepository.findAll();
    }

    public List<Medic> getAllMedicsBySpeciality(long speciality) {
        return medicRepository.findBySpecialityBySpecId_Id(speciality);
    }

    public List<Medic> getAllMedicsNotInDate(LocalDate speciality) {
        return medicRepository.findByHolidays_DateIsNotIn(speciality);
    }


    public Optional<Medic> getMedicById(long id) {
        return medicRepository.findById(id);
    }

    public Medic saveMedic(Medic medic) {
        return medicRepository.save(medic);
    }

    public void deleteMedic(long id) {
        medicRepository.deleteById(id);
    }
}