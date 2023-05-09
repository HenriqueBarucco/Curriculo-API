package com.henriquebarucco.curriculo.services;

import com.henriquebarucco.curriculo.entities.contato.Contato;
import com.henriquebarucco.curriculo.services.exceptions.CouldNotSendWhatsApp;
import io.ipinfo.api.IPinfo;
import io.ipinfo.api.errors.RateLimitedException;
import io.ipinfo.api.model.IPResponse;
import jakarta.servlet.http.HttpServletRequest;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    
    public void sendMessage(HttpServletRequest request, Contato contato) {
        try {
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            
            StringBuilder sb = new StringBuilder();
            sb.append("id=");
            sb.append(telefone);
            sb.append("&message=");
            sb.append(this.message(contato, request.getRemoteAddr()));
            
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
}
