package com.moshka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moshka.dto.PoliceManDto;
import com.moshka.enums.PoliceManStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="tbl_police_man",schema = "bicycle")
@NoArgsConstructor
public class PoliceManModel {

    public PoliceManModel(PoliceManDto dto){
        this.name=dto.getName();
        this.family=dto.getFamily();
        this.fathersName=dto.getFathersName();
        this.personnel_code=dto.getPersonnel_code();
        this.policeManStatus=PoliceManStatus.FREE;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name",nullable = false)
    private String name;


    @Column(name = "family",nullable = false)
    private String family;


    @Column(name = "personnel_code",nullable = false,unique = true)
    private String personnel_code;


    @Column(name = "fathers_name",nullable = true)
    private String fathersName;

    @JsonIgnore
    @OneToOne(mappedBy = "policeManId")
    private DossierModel dossierId;

    @Column(name = "police_man_status",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PoliceManStatus policeManStatus;


    public static PoliceManModel update(PoliceManModel old,PoliceManModel newModel){
        old.setName(newModel.getName());
        old.setFathersName(newModel.getFathersName());
        old.setFamily(newModel.getFamily());
        old.setPersonnel_code(newModel.getPersonnel_code());
        old.setDossierId(newModel.getDossierId());
        old.setPoliceManStatus(newModel.getPoliceManStatus());
        return old;
    }


}
