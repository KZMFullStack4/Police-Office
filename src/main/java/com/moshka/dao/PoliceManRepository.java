package com.moshka.dao;

import com.moshka.enums.PoliceManStatus;
import com.moshka.model.PoliceManModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PoliceManRepository extends GeneralRepositoryImpl<PoliceManModel, Long> {

    public PoliceManRepository() {
        super(PoliceManModel.class);
    }

    public Integer changePoliceManStatus(Long id, PoliceManStatus status) {
       return  super.getCurrentSession().createQuery("update PoliceManModel set policeManStatus=:value where id=:id ")
                .setParameter("value", status)
                .setParameter("id", id).executeUpdate();
    }

    public List<PoliceManModel> getBusyOrFreePolices(boolean busy) {
        if (busy) {
            return getCurrentSession().createQuery("from PoliceManModel  where policeManStatus=:status ")
                    .setParameter("status", PoliceManStatus.BUSY.toString()).list();
        } else {
            return getCurrentSession().createQuery("from PoliceManModel  where policeManStatus=:status ")
                    .setParameter("status", PoliceManStatus.FREE.toString()).list();
        }

    }


}
