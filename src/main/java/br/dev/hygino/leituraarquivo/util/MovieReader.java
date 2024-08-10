package br.dev.hygino.leituraarquivo.util;

import br.dev.hygino.leituraarquivo.model.Movie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class MovieReader {
    private final List<Movie> movies;

    public MovieReader() {
        movies = new ArrayList<>();
        readFile();
    }

    private void readFile() {
        String filePath = "movies.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Divide a linha por ponto e vírgula
                String[] values = line.split(";");
                final String title = values[0];
                final String gender = values[1];
                final String director = values[2];
                final String country = values[3];
                final LocalDate releaseDate = LocalDate.parse(values[4]);
                final double budget = Double.parseDouble(values[5]);
                movies.add(new Movie(title, gender, director, country, releaseDate, budget));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo!");
        } catch (NumberFormatException nfe) {
            System.out.println("Formato numérico inválido!");
        } catch (DateTimeParseException dtpe) {
            System.out.println("Formato de data inválida!");
        }
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Map<String, Set<String>> groupByCountry() {
        return movies.stream()
                .collect(Collectors.groupingBy(
                        Movie::country,
                        Collectors.mapping(
                                Movie::title,
                                Collectors.toCollection(TreeSet::new)
                        )
                ));
    }

    public Map<String, Double> totalBudgetByCountry() {
        return movies.stream()
                .collect(Collectors.groupingBy(
                        Movie::country,
                        Collectors.summingDouble(Movie::budget)))
                //codigo sem ordenação
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed()) // Ordena pelos valores (orçamento total), do maior para o menor
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new // Mantém a ordem de inserção
                ));
    }

}