package com.moshka.dto;

import lombok.Data;

@Data
public class TotalReportDto {
    private short isClosedFlag; //Flag Between 0 and 2 // 0 means return all (opened and closed) dossiers / 1 means return just opened dossiers / 2 means return just closed dossiers
    private short isBusyFlag; //Flag Between 0 and 2  // Same as above for this field
}
