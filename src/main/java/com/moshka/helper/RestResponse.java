package com.moshka.helper;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class RestResponse {
    public RestResponse(String message){
        this.message=message;
        this.success=true;
        this.statusCode=200;
    }
    private String message;
    private boolean success;
    private int statusCode;
    private Object objectResponse;
    public List<Object> listResponse;
}
