package com.moshka.dao;

import com.moshka.model.PlaintiffModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PlaintiffRepository extends GeneralRepositoryImpl<PlaintiffModel,Long> {

    public  PlaintiffRepository(){
        super(PlaintiffModel.class);
    }
}
