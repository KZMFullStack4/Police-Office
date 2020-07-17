package com.moshka.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//It should be consider we can add more attributes to our Models depending on our requirements // Its Just Dummy
@Entity
@Data
@Table(name="tbl_police_office",schema = "bicycle")
@NoArgsConstructor
public class PoliceOfficeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "office_location",nullable = false)
    private String officeLocation;

    @Column(name = "police_man_quantity",nullable = false)
    private int policeManQuantity;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "boss_name",nullable = false)
    private String bossName;


    @JoinTable(
            name="tbl_police_office_and_police_man",
            joinColumns = {
                    @JoinColumn(name = "police_man_id")
            },
            schema = "bicycle",
            inverseJoinColumns = @JoinColumn(name = "police_office_id")
    )
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<PoliceManModel> policeMan;

}
