package com.eme22.hospitalwebserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Holiday", indexes = {
        @Index(name = "idx_holiday_holiday", columnList = "holiday")
})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Long id;
    @Column(name = "holiday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(required = true, dataType="date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="medicId")
    private Medic medic;


}
