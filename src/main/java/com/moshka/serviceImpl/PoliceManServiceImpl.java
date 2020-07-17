package com.moshka.serviceImpl;

import com.moshka.dao.PoliceManRepository;
import com.moshka.enums.PoliceManStatus;
import com.moshka.model.PoliceManModel;
import com.moshka.service.PoliceManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliceManServiceImpl extends GeneralServiceImpl<PoliceManModel,Long>  implements PoliceManService {

    @Autowired
    private PoliceManRepository policeManRepository;

    @Override
    public Integer changePoliceManStatus(Long id, PoliceManStatus status) {
      return  policeManRepository.changePoliceManStatus(id,status);
    }

    @Override
    public List<PoliceManModel> getBusyOrFreePolices(boolean busy) {
        return policeManRepository.getBusyOrFreePolices(busy);
    }
}
