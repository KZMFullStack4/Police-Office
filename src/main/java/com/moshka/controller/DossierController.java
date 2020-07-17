package com.moshka.controller;

import com.moshka.dto.TotalReportDto;
import com.moshka.helper.RestResponse;
import com.moshka.model.DossierModel;
import com.moshka.serviceImpl.DossierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class DossierController {

    private DossierServiceImpl dossierService;

    @Qualifier("dateFormat")
    @Autowired
    private SimpleDateFormat dateFormat;

    @Qualifier("timeFormat")
    @Autowired
    private SimpleDateFormat timeFormat;

    @Autowired
    public DossierController(DossierServiceImpl dossierService){
        this.dossierService=dossierService;
    }

    public RestResponse add(DossierModel model) {
        RestResponse response = new RestResponse();
        try {
            model.setCreationDate(dateFormat.format(new Date()));
            model.setCreationTime(timeFormat.format(new Date()));
            dossierService.save(model);
            return new RestResponse("Okay !");
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
        newModel.setCreationDate(dateFormat.format(new Date()));
        newModel.setCreationTime(timeFormat.format(new Date()));
        try {
            dossierService.save(newModel);
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
        DossierModel model = dossierService.getById(id);
        try {
            dossierService.remove(model);
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
            response.setListResponse((List<Object>) (Object) dossierService.getAll());
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

    public RestResponse getAllOpenedOrClosedDossiers(boolean opened){
        RestResponse response = new RestResponse();
        try {
            response.setListResponse((List<Object>) (Object) dossierService.getAllOpenedOrClosedDossiers(opened));
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

    public RestResponse allocateDossier(Long dossierId,Long policeManId){
        RestResponse response = new RestResponse();
        try {
            if(dossierService.allocateDossiers(dossierId,policeManId)>0){
                return new RestResponse("Okay !");
            }else{
                response.setStatusCode(500);
                response.setSuccess(false);
                response.setMessage(" Server Exception occurred ! Allocating dossier has not been completed  !");
                return response;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatusCode(500);
            response.setSuccess(false);
            response.setMessage(" Server Exception occurred !");
            return response;
        }
    }


    public RestResponse closeDossier(Long dossierId){
        RestResponse response = new RestResponse();
        try {
            if(dossierService.closeDossier(dossierId)>0){
                return new RestResponse("Okay !");
            }else{
                response.setStatusCode(500);
                response.setSuccess(false);
                response.setMessage(" Server Exception occurred ! Dossier by " + dossierId + " did not change !");
                return response;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatusCode(500);
            response.setSuccess(false);
            response.setMessage(" Server Exception occurred !");
            return response;
        }
    }

    public RestResponse getTotalReport(TotalReportDto totalReportDto){
        RestResponse response = new RestResponse();
        try {
            response.setListResponse((List<Object>) (Object) dossierService.getTotalReport(totalReportDto));
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

