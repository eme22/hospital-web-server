package com.eme22.hospitalwebserver.service;

import com.eme22.hospitalwebserver.model.Speciality;
import com.eme22.hospitalwebserver.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialityService {
    private final SpecialityRepository specialityRepository;

    @Autowired
    public SpecialityService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }



    public List<Speciality> getAllSpecialities() {
        return specialityRepository.findAll();
    }

    public Optional<Speciality> getSpecialityById(long id) {
        return specialityRepository.findById(id);
    }

    public Speciality saveSpeciality(Speciality speciality) {
        return specialityRepository.save(speciality);
    }

    public void deleteSpeciality(long id) {
        specialityRepository.deleteById(id);
    }
}