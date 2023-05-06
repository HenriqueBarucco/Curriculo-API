package com.henriquebarucco.curriculo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.henriquebarucco.curriculo.entities.Curriculo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CurriculoService {

    @Value("${curriculo.data}")
    private String url;

    private final Gson gson = new Gson();
    
    public Curriculo findAll() {
        return callCurriculo();
    }
    
    private Curriculo callCurriculo() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                .url(url)
                .build();
            Response response = client.newCall(request).execute();

            assert response.body() != null;
            
            return gson.fromJson(response.body().string(), Curriculo.class);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
