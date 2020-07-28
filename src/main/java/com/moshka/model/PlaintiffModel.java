package com.moshka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="tbl_plaintiff",schema = "bicycle")
@NoArgsConstructor
public class PlaintiffModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "national_code",nullable = false,unique = true)
    private String nationalCode;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "family",nullable = false)
    private String family;

    @Column(name = "description",nullable = true)
    private String description;

    @Column(name = "address",nullable = true)
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "plaintiffId",cascade = CascadeType.REMOVE)
    private List<DossierModel> dossierList ;

    public static PlaintiffModel update(PlaintiffModel old, PlaintiffModel newModel){
        old.setAddress(newModel.getAddress());
        old.setDescription(newModel.getDescription());
        old.setFamily(newModel.getFamily());
        old.setName(newModel.getName());
        old.setNationalCode(newModel.getNationalCode());
        return  old;
    }

}
