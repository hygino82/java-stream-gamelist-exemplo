package br.dev.hygino.leituraarquivo.model;

import java.time.LocalDate;

public record Produto(
    String nome,
    String marca,
    Double preco,
    String cidade,
    String estado,
    LocalDate dataFabricacao
) {
    
}
