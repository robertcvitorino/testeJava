package com.example.projeto.Utils;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseError {

    public static ResponseEntity retornoComErro(String mensagem)
    {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body( new Gson().toJson(mensagem));
    }

}
