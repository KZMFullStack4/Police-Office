package com.moshka.serviceImpl;

import com.moshka.dao.DossierRepository;
import com.moshka.dto.TotalReportDto;
import com.moshka.dto.TotalReportResponse;
import com.moshka.model.DossierModel;
import com.moshka.service.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DossierServiceImpl extends GeneralServiceImpl<DossierModel,Long> implements DossierService {

    @Autowired
    private DossierRepository dossierRepository;

    @Override
    public List<DossierModel> getAllOpenedOrClosedDossiers(boolean opened) {
        return dossierRepository.getAllOpenedOrClosedDossiers(opened);
    }

    @Override
    public Integer closeDossier(Long dossierId) {
      return dossierRepository.closeDossier(dossierId);
    }

    @Override
    public Integer allocateDossiers(Long dossierId, Long policeManId) {
        return dossierRepository.allocateDossiers(dossierId,policeManId);
    }

    @Override
    public List<TotalReportResponse> getTotalReport(TotalReportDto reportDto) {
        return dossierRepository.getTotalReport(reportDto);
    }
}
