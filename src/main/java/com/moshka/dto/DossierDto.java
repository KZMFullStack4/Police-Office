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

    private String dossierStatus;

    private Long policeManId;

    private Long plaintiffId;

    public static DossierModel toModel(DossierDto dto){
        return new DossierModel(dto);
    }

}
