package com.eme22.hospitalwebserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Area {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "areaByAreaId", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<Medic> medicsById;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return id == area.id && Objects.equals(name, area.name) && Objects.equals(description, area.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
