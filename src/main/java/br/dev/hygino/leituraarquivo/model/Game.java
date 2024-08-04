package br.dev.hygino.leituraarquivo.model;

import java.time.LocalDate;

public record Game(
        String name,
        ConsoleType console,
        String brand,
        LocalDate releaseDate,
        Double price) implements Comparable<Game> {

    @Override
    public int compareTo(Game other) {
        return this.name.compareTo(other.name);
    }
}
