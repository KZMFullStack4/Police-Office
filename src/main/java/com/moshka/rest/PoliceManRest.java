package com.moshka.rest;

import com.moshka.controller.PoliceManController;
import com.moshka.dto.PoliceManDto;
import com.moshka.enums.PoliceManStatus;
import com.moshka.model.PoliceManModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/police-man")
public class PoliceManRest {

    private PoliceManController policeManController;

    @Autowired
    public PoliceManRest(PoliceManController policeManController) {
        this.policeManController = policeManController;
    }

    @PostMapping("/add")
    public Object add(@RequestBody PoliceManDto dto ){
        return policeManController.add(PoliceManDto.toModel(dto));
    }

    @PostMapping("/update/{id}")
    public Object update(@RequestBody PoliceManDto dto,@PathVariable Long id){

        PoliceManModel model =PoliceManDto.toModel(dto);
        switch (dto.getPoliceManStatus()){
            case"BUSY":
                model.setPoliceManStatus(PoliceManStatus.BUSY);
                break;
            case "FREE":
                model.setPoliceManStatus(PoliceManStatus.FREE);
                break;
        }
        return policeManController.update(model,id);
    }

    @PostMapping("/delete/{id}")
    public Object delete(@PathVariable Long id ){
        return policeManController.delete(id);
    }

    @PostMapping("/get-all")
    public Object getAll(){
        return policeManController.getAll();
    }
}
