package com.moshka.dto;
import lombok.*;

@Data
@NoArgsConstructor
public class TotalReportResponse {

    private String description;

    private String creationDate;

    private String creationTime;

    private String dossierStatus ;

    private Integer plaintiffId;

    private String pNationalCode;

    private String pName;

    private String pFamily;

    private String policeName;

    private String policeFamily;

    private String policePersonnel_code;

    private String policeFathersName;

    private String policeManStatus;


}
