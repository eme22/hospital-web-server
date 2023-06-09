package com.eme22.hospitalwebserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
public class Patient {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "dni")
    private long dni;
    
    @Column(name = "name1")
    private String name1;
    
    @Column(name = "name2")
    private String name2;
    
    @Column(name = "lastname1")
    private String lastname1;
    
    @Column(name = "lastname2")
    private String lastname2;
    
    @Column(name = "image")
    private String image;
    
    @Column(name = "passwordHash")
    private String passwordHash;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "age")
    private int age;
    @OneToMany(mappedBy = "patientByPatientId", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<Appointment> appointmentsById;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(getId(), patient.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
