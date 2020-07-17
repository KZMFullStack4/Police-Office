package com.moshka.controller;

import com.moshka.helper.RestResponse;
import com.moshka.model.PoliceManModel;
import com.moshka.serviceImpl.PoliceManServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PoliceManController {

    private PoliceManServiceImpl policeManService;

    @Autowired
    public PoliceManController(PoliceManServiceImpl policeManService) {
        this.policeManService = policeManService;
    }


    public RestResponse add(PoliceManModel model) {
        RestResponse response = new RestResponse();
        try {
            policeManService.save(model);
            return new RestResponse("Okay !");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatusCode(500);
            response.setSuccess(false);
            response.setMessage(" Server Exception occurred !");
            return response;
        }
    }


    public RestResponse update(PoliceManModel model, Long id) {
        RestResponse response = new RestResponse();
        PoliceManModel oldModel = policeManService.getById(id);
        PoliceManModel newModel = PoliceManModel.update(oldModel, model);
        try {
            policeManService.save(newModel);
            return new RestResponse("Okay !");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatusCode(500);
            response.setSuccess(false);
            response.setMessage(" Server Exception occurred !");
            return response;

        }
    }


    public RestResponse delete(Long id) {
        RestResponse response = new RestResponse();
        PoliceManModel model = policeManService.getById(id);
        try {
            policeManService.remove(model);
            return new RestResponse("Okay !");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatusCode(500);
            response.setSuccess(false);
            response.setMessage(" Server Exception occurred !");
            return response;
        }
    }


    public RestResponse getAll() {
        RestResponse response = new RestResponse();
        try {
            response.setListResponse((List<Object>) (Object) policeManService.getAll());
            response.setMessage(new RestResponse("Okay !").getMessage());
            response.setStatusCode(200);
            response.setSuccess(true);
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatusCode(500);
            response.setSuccess(false);
            response.setMessage(" Server Exception occurred !");
            return response;
        }
    }
}
