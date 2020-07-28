package com.moshka.service;

import com.moshka.enums.PoliceManStatus;
import com.moshka.model.PoliceManModel;

import java.util.List;

public interface PoliceManService extends  GeneralService<PoliceManModel,Long> {

    public void changePoliceManStatus(Long id, PoliceManStatus status) ;

    public List<PoliceManModel> getBusyOrFreePolices(boolean busy) ;
}
