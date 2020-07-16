package com.moshka.dao;

import com.moshka.model.DossierModel;
import org.springframework.stereotype.Repository;

@Repository
public interface DossierRepository  extends GeneralRepository<DossierModel,Long> {

}
