package com.eme22.hospitalwebserver.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Appointment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "disease")
    private String disease;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "finished")
    private boolean finished;
    
    @Column(name = "date")
    private LocalDateTime date;
    
    @Column(name = "result")
    private String result;
    @ManyToOne
    @JoinColumn(name = "medic_id", referencedColumnName = "id", nullable = false)
    private Medic medicByMedicId;
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private Patient patientByPatientId;
    @OneToMany(mappedBy = "appointmentByAppointmentId", fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Prescription> prescriptionsById;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
