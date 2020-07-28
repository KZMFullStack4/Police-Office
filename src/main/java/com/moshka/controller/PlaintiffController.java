package com.moshka.controller;

import com.moshka.helper.RestResponse;
import com.moshka.model.PlaintiffModel;
import com.moshka.serviceImpl.PlaintiffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PlaintiffController {

    private PlaintiffServiceImpl plaintiffService;

    @Autowired
    public PlaintiffController(PlaintiffServiceImpl plaintiffService) {
        this.plaintiffService = plaintiffService;
    }

    public RestResponse add(PlaintiffModel model) {
        RestResponse response = new RestResponse();
        try {
            plaintiffService.save(model);
            return new RestResponse("Okay !");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatusCode(500);
            response.setSuccess(false);
            response.setMessage(" Server Exception occurred !");
            return response;
        }
    }

    public RestResponse update(PlaintiffModel model, Long id) {
        RestResponse response = new RestResponse();
        PlaintiffModel oldModel = plaintiffService.getById(id);
        PlaintiffModel newModel = PlaintiffModel.update(oldModel, model);
        try {
            plaintiffService.save(newModel);
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
        PlaintiffModel model = plaintiffService.getById(id);
        try {
            plaintiffService.remove(model);
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
            response.setListResponse((List<Object>) (Object) plaintiffService.getAll());
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
