package br.dev.hygino.leituraarquivo.model;

import java.time.LocalDate;

public record Movie(
        String title,
        String gender,
        String director,
        String country,
        LocalDate releaseDate,
        Double budget
) {
}
