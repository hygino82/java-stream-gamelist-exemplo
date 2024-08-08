package br.dev.hygino.leituraarquivo.model;

public record Book(
        String title,
        String author,
        String publisher,
        Integer releaseYear,
        Integer totalPages) {

}
