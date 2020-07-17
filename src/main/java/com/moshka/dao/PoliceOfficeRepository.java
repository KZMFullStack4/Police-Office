package com.moshka.dao;

import com.moshka.model.PoliceOfficeModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PoliceOfficeRepository extends GeneralRepositoryImpl<PoliceOfficeModel,Long> {
    public  PoliceOfficeRepository(){
        super(PoliceOfficeModel.class);
    }
}

