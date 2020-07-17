package com.moshka.rest;

import com.moshka.controller.DossierController;
import com.moshka.dto.DossierDto;
import com.moshka.enums.DossierStatus;
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
        }
        model.setPoliceManId(policeManModel);
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
        model.setPoliceManId(policeManModel);
        model.setPlaintiffId(plaintiffModel);
        model.setCreationDate(new Date().toString());
        model.setCreationTime(String.valueOf(new Date().getTime()));
        model.setDossierStatus(DossierStatus.CLOSED);
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

}
