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

    private String plaintiffNationalCode;

    private String plaintiffName;

    private String plaintiffFamily;

    private String policeName;

    private String policeFamily;

    private String policePersonnelCode;

    private String policeFathersName;

    private String policeManStatus;

}
