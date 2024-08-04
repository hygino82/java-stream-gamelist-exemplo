package br.dev.hygino.leituraarquivo.model;

import java.time.LocalDate;

public record Game(
        String name,
        ConsoleType console,
        String brand,
        LocalDate releaseDate,
        Double price) {

}
