package com.eme22.hospitalwebserver.util;

import com.eme22.hospitalwebserver.dao.AppointmentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Log4j2
public class UpdateFinishedAppointments {

    AppointmentRepository repository;

    @Autowired
    public UpdateFinishedAppointments(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Scheduled(cron = "0 0 * * * *")
    void findAndFinishAppointments() {

        log.info("Updating Past Appointments");

        repository.findByFinishedIsFalse().forEach(appointment -> {

            if (appointment.getDate().isAfter(LocalDateTime.now())) {
                repository.updateFinishedById(true, appointment.getId() );
            }

        });

    }

}
