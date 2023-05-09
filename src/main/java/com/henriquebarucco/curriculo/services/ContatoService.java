package com.henriquebarucco.curriculo.services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.henriquebarucco.curriculo.entities.contato.Contato;
import com.henriquebarucco.curriculo.services.exceptions.CouldNotSendWhatsApp;
import io.ipinfo.api.IPinfo;
import io.ipinfo.api.errors.RateLimitedException;
import io.ipinfo.api.model.IPResponse;
import jakarta.servlet.http.HttpServletRequest;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.json.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ContatoService {
    private final OkHttpClient client = new OkHttpClient().newBuilder().build();
    
    @Value("${curriculo.token_ip}")
    private String token_ip;
    
    @Value("${curriculo.telefone}")
    private String telefone;
    
    @Value("${curriculo.api}")
    private String api;

    @Value("${curriculo.token}")
    private String token;
    
    public void sendMessage(Contato contato) {
        try {
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            
            StringBuilder sb = new StringBuilder();
            sb.append("id=");
            sb.append(telefone);
            sb.append("&message=");
            sb.append(this.message(contato, this.getClientIp()));
            
            RequestBody body = RequestBody.create(mediaType, sb.toString());
            Request requestApi = new Request.Builder()
                .url(api)
                .method("POST", body)
                .addHeader("Authorization", "Bearer " + token)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
            client.newCall(requestApi).execute();
        } catch (Exception e) {
            throw new CouldNotSendWhatsApp();
        }
    }
    
    private String message(Contato contato, String ipAddress) throws RateLimitedException {
        StringBuilder sb = new StringBuilder();
        IPinfo ipInfo = new IPinfo.Builder().setToken(token_ip).build();
        
        IPResponse response = ipInfo.lookupIP(ipAddress);

        System.out.println(response);
        
        sb.append("Nova mensagem do Currículo-API:\n\n");
        sb.append(contato.mensagem());
        sb.append("\n\nNome: ").append(contato.nome());
        sb.append("\nContato: ").append(contato.contato());
        sb.append("\n\n" + response.getCity());
        
        return sb.toString();
    }

    private String getClientIp() throws Exception {
        URL url = new URL("http://ip-api.com/json/?fields=query");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JsonObject jsonObject = new Gson().fromJson(response.toString(), JsonObject.class);
        JsonElement jsonElement = (JsonElement) jsonObject.get("query");
        String ipAddress = jsonElement.getAsString();
        return ipAddress;
    }
}
