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
    @Override
    public String toString() {
        return String.format("Movie[title=%s, gender=%s, director=%s, country=%s, releaseDate=%s, budget=%.2f]",
                title, gender, director, country, releaseDate, budget);
    }


    public String getTitle() {
        return title;
    }


    public Double getBudget() {
        return budget;
    }


    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getCountry() {
        return country;
    }


    public String getDirector() {
        return director;
    }


    public String getGender() {
        return gender;
    }
}
