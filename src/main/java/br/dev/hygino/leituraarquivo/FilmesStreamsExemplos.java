package br.dev.hygino.leituraarquivo;

import br.dev.hygino.leituraarquivo.util.MovieReader;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class FilmesStreamsExemplos {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        final var movieReader = new MovieReader();

        // System.out.println("Listar todos os filmes");
        // movieReader.getMovies().forEach(System.out::println);
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
        final List<String> buscarFilmesPorPais = movieReader.findMoviesByCountry(pais);
        buscarFilmesPorPais.forEach(System.out::println);

        System.out.println("\nQuantidade de filmes lançadas por ano");
        final var quantidadeFilmesPorAno = movieReader.countMoviesByYear();
        quantidadeFilmesPorAno.forEach((ano, filmes) -> System.out.printf("%s: %d\n", ano, filmes));

        System.out.println("Pais com mais filmes gravados");
        final var paisComMaisFilmes = movieReader.countryWithMoreMovies();
        System.out.println(paisComMaisFilmes);

        System.out.println("\nPais com maior gastos em filmes");
        final var paisMaiorGasto = movieReader.countryWithMoreMoviesBudget();
        System.out.printf("%s : %.2f \n", paisMaiorGasto.getKey(), paisMaiorGasto.getValue());

        System.out.println("\nPaís com menor gasto em filmes");
        final var paisMenorGasto = movieReader.countryWithLessMoviesBudget();
        System.out.printf("%s : %.2f \n", paisMenorGasto.getKey(), paisMenorGasto.getValue());

        final var filmesPorMes = movieReader.groupMoviesByMonth();
        filmesPorMes.forEach((mes, filmes) -> {
            System.out.println(mes);
            filmes.forEach(System.out::println);
            System.out.println();
        });

        Set<DiretorGastos> gastosDiretores = new TreeSet<>();
        final var gastosPorDiretor = movieReader.bugetByDirector();
        gastosPorDiretor.forEach((diretor, valores) -> {
            System.out.println(diretor);
            valores.forEach(x -> {
                System.out.printf("%.2f\n", x);
            });
            double soma = valores.stream()
                    .reduce(0.0, Double::sum);

            // System.out.printf("A soma dos diretor %s foi de %.2f\n", diretor, soma);
            gastosDiretores.add(new DiretorGastos(diretor, soma));
            System.out.println();
        });
        System.out.println("\nTotal de gastos dos diretores");
        gastosDiretores.forEach(System.out::println);
        System.out.println("O diretor com mais gastos foi: " + gastosDiretores.toArray()[0]);

        final var diretor = "James Cameron";
        final Set<String> filmesDoDiretor = movieReader.getMoviesByDirector(diretor);
        System.out.println("\nFilmes do Diretor: " + diretor);
        filmesDoDiretor.forEach(System.out::println);
        System.out.println("Seu filme mais caro foi:");

        final var filmeMaisCaroDiretor = movieReader.findMovieByDirectorWithHighestBudget(diretor);
        System.out.println(filmeMaisCaroDiretor);

        System.out.println("\nLista de filmes com titulo e orçamento");
        final var listaTituloOrcamento = movieReader.moviesByDirectorWithBudget(diretor);
        listaTituloOrcamento.forEach((titulo, orcamento) -> System.out.printf("%s: %.2f\n", titulo, orcamento));
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

record DiretorGastos(String nome, Double valor) implements Comparable<DiretorGastos> {

    @Override
    public String toString() {
        return nome + String.format(": %.2f", valor);
    }

    @Override
    public int compareTo(DiretorGastos other) {
        return -this.valor.compareTo(other.valor);
    }
}