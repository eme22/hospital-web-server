package com.eme22.hospitalwebserver.repository;

import com.eme22.hospitalwebserver.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* Generated by Spring Data Generator on 23/05/2023
*/
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>, JpaSpecificationExecutor<Appointment> {
    @Transactional
    @Modifying
    @Query("update Appointment a set a.finished = ?1 where a.id = ?2")
    int updateFinishedById(boolean finished, long id);
    @Query("select a from Appointment a where a.medicByMedicId.id = ?1")
    List<Appointment> findByMedicByMedicId_Id(long id);

    @Query("select a from Appointment a where a.medicByMedicId.dni = ?1")
    List<Appointment> findByMedicByMedicId_Dni(long dni);

    List<Appointment> findByFinishedIsFalse();

    //List<Appointment> findByMedicByMedicId_Id(@NonNull long id);

    List<Appointment> findByPatientByPatientId_Id(@NonNull long id);

    //List<Appointment> findByMedicByMedicId_Dni(@NonNull long dni);

    List<Appointment> findByPatientByPatientId_Dni(@NonNull long dni);

    List<Appointment> findByPatientByPatientId_DniAndFinished(long dni, boolean finished);



}
