package com.trabalho.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends  StandardError{

    private List<FieldMessage> erros = new ArrayList<>();

    public  ValidationError(Long timeStamp, Integer status, String error, String message, String path){
        super(timeStamp, status, error, message, path);
    }

    public  List<FieldMessage> getErros(){
        return  erros;
    }

    public void addErros (String fieldName, String message){
        this.erros.add(new FieldMessage(fieldName, message));
    }



}
