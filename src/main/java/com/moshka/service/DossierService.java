package com.moshka.service;

import com.moshka.dto.TotalReportDto;
import com.moshka.dto.TotalReportResponse;
import com.moshka.model.DossierModel;

import java.util.List;

public interface DossierService  extends GeneralService<DossierModel,Long>{

    public List<DossierModel> getAllOpenedOrClosedDossiers(boolean opened);

    public Integer closeDossier(Long dossierId);

    public Integer allocateDossiers(Long dossierId,Long policeManId);

    public List<TotalReportResponse>getTotalReport(TotalReportDto reportDto);

}
