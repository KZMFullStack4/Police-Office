package com.moshka.service;

import com.moshka.dto.TotalReportDto;
import com.moshka.dto.TotalReportResponse;
import com.moshka.enums.DossierStatus;
import com.moshka.model.DossierModel;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public interface DossierService  extends GeneralService<DossierModel,Long>{

    public List<DossierModel> getAllOpenedOrClosedDossiers(boolean opened);

    public Integer closeDossier(Long dossierId);

    public Integer allocateDossiers(Long dossierId,Long policeManId);

    public List<TotalReportResponse>getTotalReport(TotalReportDto reportDto);

}
