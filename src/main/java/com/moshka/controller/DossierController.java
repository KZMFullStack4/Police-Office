package com.moshka.controller;

import com.moshka.helper.RestResponse;
import com.moshka.model.DossierModel;
import com.moshka.serviceImpl.DossierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DossierController {

    private DossierServiceImpl dossierService;

    @Autowired
    public DossierController(DossierServiceImpl dossierService){
        this.dossierService=dossierService;
    }

    public RestResponse add(DossierModel model) {
        RestResponse response = new RestResponse();
        try {
            dossierService.save(model);
            return new RestResponse("درخواست با موفقیت انجام شد");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatusCode(500);
            response.setSuccess(false);
            response.setMessage(" Server Exception occurred !");
            return response;
        }
    }


    public RestResponse update(DossierModel model, Long id) {
        RestResponse response = new RestResponse();
        DossierModel oldModel = dossierService.getById(id);
        DossierModel newModel = DossierModel.update(oldModel, model);
        try {
            dossierService.save(newModel);
            return new RestResponse("درخواست با موفقیت انجام شد");
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
        DossierModel model = dossierService.getById(id);
        try {
            dossierService.remove(model);
            return new RestResponse("درخواست با موفقیت انجام شد");
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
            response.setListResponse((List<Object>) (Object) dossierService.getAll());
            response.setMessage(new RestResponse("درخواست با موفقیت انجام شد").getMessage());
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

