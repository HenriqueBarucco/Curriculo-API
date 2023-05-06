package com.henriquebarucco.curriculo.entities;

import java.util.List;

public record Habilidades(
    Tecnologias tecnologias,
    List<String> metodologiasAgeis,
    List<String> ferramentas,
    List<String> devops,
    List<String> idiomas
) {
}
