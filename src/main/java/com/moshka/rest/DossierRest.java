package com.moshka.rest;

import com.moshka.controller.DossierController;
import com.moshka.dto.DossierDto;
import com.moshka.dto.TotalReportDto;
import com.moshka.enums.DossierStatus;
import com.moshka.enums.PoliceManStatus;
import com.moshka.model.DossierModel;
import com.moshka.model.PlaintiffModel;
import com.moshka.model.PoliceManModel;
import com.moshka.serviceImpl.PlaintiffServiceImpl;
import com.moshka.serviceImpl.PoliceManServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/dossier")
public class DossierRest {

    private DossierController dossierController;
    private PoliceManServiceImpl policeManService;
    private PlaintiffServiceImpl plaintiffService;

    @Autowired
    public DossierRest(DossierController dossierController, PoliceManServiceImpl policeManService, PlaintiffServiceImpl plaintiffService) {
        this.dossierController = dossierController;
        this.policeManService = policeManService;
        this.plaintiffService = plaintiffService;
    }

    @PostMapping("/add")
    public Object add(@RequestBody  DossierDto dto){
        PoliceManModel policeManModel = policeManService.getById(dto.getPoliceManId());
        PlaintiffModel plaintiffModel = plaintiffService.getById(dto.getPlaintiffId());
        DossierModel model =DossierDto.toModel(dto);
        if(policeManModel!=null){
            model.setDossierStatus(DossierStatus.CLOSED);
            policeManService.changePoliceManStatus(policeManModel.getId(), PoliceManStatus.BUSY);
            model.setPoliceManId(policeManModel);
        }else{
            model.setDossierStatus(DossierStatus.OPENED);
        }
        model.setPlaintiffId(plaintiffModel);
        model.setCreationDate(new Date().toString());
        model.setCreationTime(String.valueOf(new Date().getTime()));

        return dossierController.add(model);
    }


    @PutMapping("/update/{id}")
    public Object update(@RequestBody DossierDto dto ,@PathVariable("id") Long id){
        PoliceManModel policeManModel = policeManService.getById(dto.getPoliceManId());
        PlaintiffModel plaintiffModel = plaintiffService.getById(dto.getPlaintiffId());
        DossierModel model =DossierDto.toModel(dto);
        if(policeManModel!=null){
            model.setDossierStatus(DossierStatus.CLOSED);
            policeManService.changePoliceManStatus(policeManModel.getId(), PoliceManStatus.BUSY);
            model.setPoliceManId(policeManModel);
        }else{
            model.setDossierStatus(DossierStatus.OPENED);
        }
        model.setPlaintiffId(plaintiffModel);
        model.setCreationDate(new Date().toString());
        model.setCreationTime(new Date().toString());
        return dossierController.update(model,id);
    }

    @DeleteMapping("/delete/{id}")
    public Object delete(@PathVariable("id") Long id){
        return dossierController.delete(id);
    }

    @GetMapping("/get-all")
    public Object getAll(){
        return dossierController.getAll();
    }

    @GetMapping("/get-all-closed-opened-dossiers/{opened}")
    public Object getAllClosedDossiers(@PathVariable boolean opened){
        return dossierController.getAllOpenedOrClosedDossiers(opened);
    }

    @PostMapping("/close-dossier/{id}")
    public Object closeDossier(@PathVariable Long id){
        return dossierController.closeDossier(id);
    }

    @PostMapping("/allocate-dossier/{dossierId}/{policeManId}")
    public Object allocateDossier(@PathVariable Long dossierId,@PathVariable Long policeManId){
        return dossierController.allocateDossier(dossierId,policeManId);
    }

    @PostMapping("/get-total-report")
    public  Object getTotalReport(@RequestBody TotalReportDto dto){
        return  dossierController.getTotalReport(dto);
    }
}

