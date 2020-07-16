package com.moshka.daoImpl;

import com.moshka.model.DossierModel;

public class DossierRepositoryImpl extends  GeneralRepositoryImpl<DossierModel,Long>  {

    public DossierRepositoryImpl() {
        super(DossierModel.class);
    }
}
