package com.henriquebarucco.curriculo.exceptions;

import com.henriquebarucco.curriculo.services.exceptions.CouldNotSendWhatsApp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(CouldNotSendWhatsApp.class)
    public ResponseEntity<StandardError> planningExists(CouldNotSendWhatsApp e, HttpServletRequest request) {
        String error = "Não foi possível enviar a mensagem. Entre em contato: (16) 991033799";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
