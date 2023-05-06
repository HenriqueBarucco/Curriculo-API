package com.henriquebarucco.curriculo.entities;

import java.util.List;

public record Curriculo(
    Informacoes informacoes,
    String resumo,
    List<Formacao> formacoes,
    List<HistoricoProfissional> historicoProfissional,
    List<Projeto> projetos,
    Habilidades habilidades,
    List<String> certificacoes
) {
}
