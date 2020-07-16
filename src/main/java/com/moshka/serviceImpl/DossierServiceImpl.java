package com.moshka.serviceImpl;

import com.moshka.daoImpl.GeneralRepositoryImpl;
import com.moshka.model.DossierModel;
import com.moshka.service.GeneralService;
import org.springframework.stereotype.Service;

@Service
public class DossierServiceImpl extends GeneralRepositoryImpl<DossierModel,Long> {
}
