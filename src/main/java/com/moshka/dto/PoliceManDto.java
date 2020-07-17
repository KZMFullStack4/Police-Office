package com.moshka.dto;

import com.moshka.enums.PoliceManStatus;
import com.moshka.model.DossierModel;
import com.moshka.model.PoliceManModel;

import javax.persistence.*;

public class PoliceManDto {

    private String name;

    private String family;

    private String personnel_code;

    private String fathersName;

    private String dossierId;

    private String policeManStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getPersonnel_code() {
        return personnel_code;
    }

    public void setPersonnel_code(String personnel_code) {
        this.personnel_code = personnel_code;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getDossierId() {
        return dossierId;
    }

    public void setDossierId(String dossierId) {
        this.dossierId = dossierId;
    }

    public String getPoliceManStatus() {
        return policeManStatus;
    }

    public void setPoliceManStatus(String policeManStatus) {
        this.policeManStatus = policeManStatus;
    }
    public static PoliceManModel toModel(PoliceManDto dto){
        return new PoliceManModel(dto);
    }
}
