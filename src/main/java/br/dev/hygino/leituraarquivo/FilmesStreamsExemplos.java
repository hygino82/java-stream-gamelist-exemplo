package br.dev.hygino.leituraarquivo;

import br.dev.hygino.leituraarquivo.util.MovieReader;

import java.util.List;
import java.util.Locale;

public class FilmesStreamsExemplos {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        final var movieReader = new MovieReader();

        //System.out.println("Listar todos os filmes");
        //movieReader.getMovies().forEach(System.out::println);
        // System.out.println(movieReader.getMovies().size());

        /*
         * System.out.println("Agrupar filmes por país");
         * final Map<String, Set<String>> filmesPorPaisOrdenadoPorTitulo =
         * movieReader.groupByCountry();
         * filmesPorPaisOrdenadoPorTitulo.forEach((pais, filmes) -> {
         * System.out.println(pais);
         * filmes.forEach(System.out::println);
         * System.out.println();
         * });
         * List<BudgetCountry> lista = new ArrayList<>();
         * System.out.println("Agrupar gastos de filmes por país");
         * final Map<String, Double> gastosFilmesPorPais =
         * movieReader.totalBudgetByCountry();
         * gastosFilmesPorPais.forEach((pais, gastos) -> {
         * // System.out.printf("%s: %.2f\n", pais, gastos);
         * lista.add(new BudgetCountry(pais, gastos));
         * });
         *
         * lista.stream().limit(3).sorted().forEach(f -> System.out.printf("%s: %.2f\n",
         * f.name(), f.budget()));
         *
         * System.out.println("\nListar gastos de filmes por gênero");
         * final Map<String, Double> gastosPorGenero =
         * movieReader.groupByGenderAndTotalBudget();
         * gastosPorGenero.forEach((genero, gasto) -> System.out.printf("%s: %.2f\n",
         * genero, gasto));
         *
         * final LocalDate dataInicial = LocalDate.of(1981, Month.APRIL, 10);
         * final LocalDate dataFinal = LocalDate.of(1998, Month.SEPTEMBER, 17);
         * System.out.println("\nFilmes lançados entre " + dataFinal + " " + dataFinal);
         * final var filmesLancadosNoIntervalo =
         * movieReader.findMoviesReleasedBetweenGroupByMonth(dataInicial, dataFinal);
         * filmesLancadosNoIntervalo.forEach((mes, filmes) -> {
         * System.out.println(mes);
         * filmes.forEach(System.out::println);
         * });
         *
         * System.out.println("\nContar filmes por diretor");
         * final var filmesPorDiretor = movieReader.countMoviesByDirector();
         * filmesPorDiretor.forEach((diretor, quantidade) ->
         * System.out.printf("%s: %d\n", diretor, quantidade));
         */
        final var pais = "Brasil";
        System.out.println("\nBuscar filmes do " + pais);
        final List<String> buscarFilmesPorPais =
                movieReader.findMoviesByCountry(pais);
        buscarFilmesPorPais.forEach(System.out::println);

    }
}

record BudgetCountry(
        String country,
        Double budget) implements Comparable<BudgetCountry> {

    @Override
    public int compareTo(BudgetCountry other) {
        return -this.budget.compareTo(other.budget);
    }
}