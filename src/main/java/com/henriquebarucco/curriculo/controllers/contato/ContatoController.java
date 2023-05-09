package com.henriquebarucco.curriculo.controllers.contato;

import com.henriquebarucco.curriculo.entities.contato.Contato;
import com.henriquebarucco.curriculo.services.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

@EnableWebMvc
@Tag(name = "Entre em contato", description = "Endpoints para buscar o Currículo inteiro ou apenas as partes necessárias.")
@RestController
@RequestMapping(path = "/v1")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;
    
    @Autowired
    private HttpServletRequest request;
    
    @Operation(summary = "Entre em contato comigo.", description = "Envie uma mensagem agora mesmo para o meu WhatsApp, basta informar nos campos a mensagem e pronto, chegará em meu WhatsApp Pessoal.")
    @PostMapping(value = "/contato")
    public ResponseEntity<String> curriculo(@RequestParam String mensagem, @RequestParam String nome, @RequestParam String contato) {
        Contato sendContato = new Contato(mensagem, nome, contato);
        String userIpAddress = request.getHeader("CF-Connecting-IP");
        contatoService.sendMessage(sendContato);
        return ResponseEntity.ok("Mensagem enviada com sucesso! " + userIpAddress);
    }
}
