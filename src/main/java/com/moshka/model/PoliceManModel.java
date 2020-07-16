package com.moshka.model;

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


    @OneToOne(cascade = CascadeType.PERSIST)
    private Dossier dossierId;

    @Column(name = "police_man_status",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PoliceManStatus policeManStatus;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name="tbl_police_office_and_police_man",
            joinColumns = {
                    @JoinColumn(name = "police_man_id")
            },
            schema = "bicycle",
            inverseJoinColumns = @JoinColumn(name = "police_office_id")
    )
    private List<PoliceOfficeModel> policeOfficeId;


}
