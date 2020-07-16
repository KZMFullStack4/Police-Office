package com.moshka.dto;

import com.moshka.model.DossierModel;
import lombok.Data;


@Data
public class DossierDto {

    private Long id;

    private String dossierNumber;

    private String description;

    private String creationDate;

    private String creationTime;

    private String dossierStatus ;//enum

    private Long policeManId;

    private Long plaintiffId;
    public static DossierModel toModel(DossierDto dto){
        return new DossierModel(dto);
    }

//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getDossierNumber() {
//        return dossierNumber;
//    }
//
//    public void setDossierNumber(String dossierNumber) {
//        this.dossierNumber = dossierNumber;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getCreationDate() {
//        return creationDate;
//    }
//
//    public void setCreationDate(String creationDate) {
//        this.creationDate = creationDate;
//    }
//
//    public String getCreationTime() {
//        return creationTime;
//    }
//
//    public void setCreationTime(String creationTime) {
//        this.creationTime = creationTime;
//    }
//
//    public String getDossierStatus() {
//        return dossierStatus;
//    }
//
//    public void setDossierStatus(String dossierStatus) {
//        this.dossierStatus = dossierStatus;
//    }
//
//    public Long getPoliceManId() {
//        return policeManId;
//    }
//
//    public void setPoliceManId(Long policeManId) {
//        this.policeManId = policeManId;
//    }
//
//    public Long getPlaintiffId() {
//        return plaintiffId;
//    }
//
//    public void setPlaintiffId(Long plaintiffId) {
//        this.plaintiffId = plaintiffId;
//    }
}
