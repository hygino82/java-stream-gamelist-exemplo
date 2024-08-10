package br.dev.hygino.leituraarquivo.util;

import java.util.*;

public class FilmesStreamsExemplos {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        final var movieReader = new MovieReader();

        /*System.out.println("Listar todos os filmes");
        movieReader.getMovies().forEach(System.out::println);*/

        System.out.println("Agrupar filmes por país");
        final Map<String, Set<String>> filmesPorPaisOrdenadoPorTitulo = movieReader.groupByCountry();
        filmesPorPaisOrdenadoPorTitulo.forEach((pais, filmes) -> {
            System.out.println(pais);
            filmes.forEach(System.out::println);
            System.out.println();
        });
        List<BudgetCountry> lista = new ArrayList<>();
        System.out.println("Agrupar gastos de filmes por país");
        final Map<String, Double> gastosFilmesPorPais = movieReader.totalBudgetByCountry();
        gastosFilmesPorPais.forEach((pais, gastos) -> {
           // System.out.printf("%s: %.2f\n", pais, gastos);
            lista.add(new BudgetCountry(pais, gastos));
        });

        lista.stream().limit(3).sorted().forEach(f -> System.out.printf("%s: %.2f\n", f.name(), f.budget()));
    }
}

record BudgetCountry(
        String name,
        Double budget
) implements Comparable<BudgetCountry> {

    @Override
    public int compareTo(BudgetCountry other) {
        return -this.budget.compareTo(other.budget);
    }
}