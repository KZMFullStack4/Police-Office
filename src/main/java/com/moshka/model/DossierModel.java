package com.moshka.model;

import com.moshka.dto.DossierDto;
import com.moshka.enums.DossierStatus;
import lombok.*;
import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name="tbl_dossier",schema = "bicycle")
@NoArgsConstructor
public class DossierModel {

    public DossierModel(DossierDto dto){
        this.description=dto.getDescription();
        this.dossierNumber=dto.getDossierNumber();
        this.creationDate=dto.getCreationDate();
        this.creationTime=dto.getCreationTime();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dossier_number",nullable = false,unique = true)
    private String dossierNumber;

    @Column(name = "description",nullable = true)
    private String description;


    @Column(name = "creation_date",nullable = false)
    private String creationDate;


    @Column(name = "creation_time",nullable = true)
    private String creationTime;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "dossier_Status")
    private DossierStatus dossierStatus ;


    @OneToOne(cascade = CascadeType.PERSIST)
    private PoliceManModel policeManId;


    @JoinColumn(name = "plaintiff_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PlaintiffModel plaintiffId;

    public static DossierModel update(DossierModel oldModel,DossierModel newModel){
        oldModel.setCreationDate(newModel.getCreationDate());
        oldModel.setCreationTime(newModel.getCreationTime());
        oldModel.setDossierNumber(newModel.getDossierNumber());
        oldModel.setDescription(newModel.getDescription());
        oldModel.setDossierStatus(newModel.getDossierStatus());
        oldModel.setPoliceManId(newModel.getPoliceManId());
        oldModel.setPlaintiffId(newModel.getPlaintiffId());
        return oldModel;
    }
}
