package com.moshka.rest;

import com.moshka.controller.PoliceManController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/police-man")
public class PoliceManRest {

    private PoliceManController policeManController;

    @Autowired
    public PoliceManRest(PoliceManController policeManController) {
        this.policeManController = policeManController;
    }

//    public Objetct add(PoliceMan)
}
