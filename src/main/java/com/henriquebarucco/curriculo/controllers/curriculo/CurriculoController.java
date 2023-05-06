package com.henriquebarucco.curriculo.controllers.curriculo;

import com.henriquebarucco.curriculo.entities.*;
import com.henriquebarucco.curriculo.services.CurriculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Currículo", description = "Endpoints para buscar o Currículo inteiro ou apenas as partes necessárias.")
@RestController
@RequestMapping(path = "/")
public class CurriculoController {

    @Autowired
    private CurriculoService curriculoService;

    @Operation(
        summary = "Consultar todo o curriculo.",
        description = "Consulta de todas as informações do curriculo."
    )
    @GetMapping(value = "/all")
    public ResponseEntity<Curriculo> curriculo() {
        Curriculo curriculo = curriculoService.findAll();
        return ResponseEntity.ok().body(curriculo);
    }

    @Operation(
        summary = "Informações pessoais.",
        description = "Consulta de todas as minhas informações pessoais."
    )
    @GetMapping(value = "/info")
    public ResponseEntity<Informacoes> informacoes() {
        Informacoes informacoes = curriculoService.findAll().informacoes();
        return ResponseEntity.ok().body(informacoes);
    }

    @Operation(
        summary = "Resumo pessoal.",
        description = "...."
    )
    @GetMapping(value = "/resume", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> resumo() {
        String resumo = curriculoService.findAll().resumo();
        return ResponseEntity.ok()
            .header("Content-Type", "application/json; charset=UTF-8")
            .body("{ \"resumo\": \"" + resumo + "\" }");
    }

    @Operation(
        summary = "Formações.",
        description = "...."
    )
    @GetMapping(value = "/formation")
    public ResponseEntity<List<Formacao>> formacoes() {
        List<Formacao> formacoes = curriculoService.findAll().formacoes();
        return ResponseEntity.ok().body(formacoes);
    }

    @Operation(
        summary = "Historico Profissional.",
        description = "...."
    )
    @GetMapping(value = "/professional")
    public ResponseEntity<List<HistoricoProfissional>> historicoProfissional() {
        List<HistoricoProfissional> historicoProfissional = curriculoService.findAll().historicoProfissional();
        return ResponseEntity.ok().body(historicoProfissional);
    }

    @Operation(
        summary = "Projetos.",
        description = "...."
    )
    @GetMapping(value = "/projects")
    public ResponseEntity<List<Projeto>> projetos() {
        List<Projeto> projetos = curriculoService.findAll().projetos();
        return ResponseEntity.ok().body(projetos);
    }

    @Operation(
        summary = "Habilidades.",
        description = "...."
    )
    @GetMapping(value = "/abilities")
    public ResponseEntity<Habilidades> habilidades() {
        Habilidades habilidades = curriculoService.findAll().habilidades();
        return ResponseEntity.ok().body(habilidades);
    }

    @Operation(
        summary = "Certificações.",
        description = "...."
    )
    @GetMapping(value = "/certificates")
    public ResponseEntity<List<String>> certificacoes() {
        List<String> certificacoes = curriculoService.findAll().certificacoes();
        return ResponseEntity.ok().body(certificacoes);
    }
}

