package com.moshka.rest;

import com.moshka.controller.PlaintiffController;
import com.moshka.model.PlaintiffModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plaintiff")
public class PlaintiffRest {
    
    private PlaintiffController plaintiffController;

    @Autowired
    public PlaintiffRest(PlaintiffController plaintiffController) {
        this.plaintiffController = plaintiffController;
    }
    
    @PostMapping("/add")
    public Object add(@RequestBody PlaintiffModel model ){
        return plaintiffController.add(model);
    }

    
    @PostMapping("/update/{id}")
    public Object update(@RequestBody PlaintiffModel model,@PathVariable Long id){
        return plaintiffController.update(model,id);
    }

    @PostMapping("/delete/{id}")
    public Object delete(@PathVariable Long id ){
        return plaintiffController.delete(id);
    }

    @PostMapping("/get-all")
    public Object getAll(){
        return plaintiffController.getAll();
    }
    
    
}
