package com.eme22.hospitalwebserver.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Medic {
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
    @OneToMany(mappedBy = "medicByMedicId", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<Appointment> appointmentsById;
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn(name = "area_id", referencedColumnName = "id", nullable = false)
    private Area areaByAreaId;
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn(name = "spec_id", referencedColumnName = "id", nullable = false)
    private Speciality specialityBySpecId;

    @OneToMany(mappedBy="medic", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Holiday> holidays;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Medic medic = (Medic) o;
        return Objects.equals(getId(), medic.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
