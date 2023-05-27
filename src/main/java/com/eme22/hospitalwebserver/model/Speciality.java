package com.eme22.hospitalwebserver.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Speciality {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;
    
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "specialityBySpecId")
    @ToString.Exclude
    private Collection<Medic> medicsById;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Speciality that = (Speciality) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
