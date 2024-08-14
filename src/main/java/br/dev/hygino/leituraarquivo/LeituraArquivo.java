package br.dev.hygino.leituraarquivo;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import br.dev.hygino.leituraarquivo.model.ConsoleType;
import br.dev.hygino.leituraarquivo.model.Game;
import br.dev.hygino.leituraarquivo.util.GameReader;

public class LeituraArquivo {

    public static void main(String[] args) {
        GameReader reader = new GameReader();

        // reader.mostrarValores();

        ConsoleType consoleType = ConsoleType.MEGA_DRIVE;
        final var consoleGames = reader.findGamesByConsole(consoleType);
        System.out.println();
        consoleGames.forEach(System.out::println);

        System.out.println("Jogos agrupados por console");
        reader.groupGamesByConsole().forEach((console, games) -> {
            System.out.println(console);
            games.stream()
                    .sorted()
                    .forEach(System.out::println);
            System.out.println();
        });

        final var inicio = LocalDate.of(2010, Month.MARCH, 7);
        final var fim = LocalDate.of(2015, Month.DECEMBER, 25);
        final List<Game> jogosPorPeriodo = reader.findGamesReleasedBetween(inicio, fim);
        System.out.printf("Jogos lançados entre %s e %s\n", inicio, fim);
        jogosPorPeriodo.stream().sorted().forEach(System.out::println);

        final long contarJogosLancadosEntre = reader.countGamesReleasedBetween(inicio, fim);
        System.out.printf("Foram lançados %d jogos nesse período\n", contarJogosLancadosEntre);

        final var gasto = reader.countTotalGamePrice();
        System.out.printf("Gasto total com jogos: %.2f\n", gasto);

        final var gastoEmJogosPorConsole = reader.gameCostByConsole();
        gastoEmJogosPorConsole.forEach((console, price) -> System.out.printf("\n%s US$%.2f\n", console, price));

        System.out.println("Lista de jogos agrupados por ano de lançamento");
        final var jogosPorAno = reader.groupGamesByYear();
        jogosPorAno.forEach((ano, jogos) -> {
            System.out.printf("Ano: %d\n", ano);
            jogos.stream().sorted().forEach(System.out::println);
        });

        final var jogosPorConsole = reader.listarJogosAgrupadosPorConsole();
        System.out.println("\nJogos agrupados por console");
        jogosPorConsole.forEach((c, j) -> {
            System.out.println(c.name());
            j.forEach(System.out::println);
            System.out.println();
        });

        final var contagemJogosConsole = reader.contarJogosCadastradosPorConsole();
        contagemJogosConsole.forEach((console, quantidade) -> System.out.println(console + ": " + quantidade));

        final var consoleComMenorCadastro = reader.consoleComMenorNumeroJogosCadastrados();
        System.out.println("\nConsole com menor quantidade de jogos cadastrados: " + consoleComMenorCadastro);

        final var menorCusto = reader.buscarJogoComMenorCustoDoConsole(consoleType);
        System.out.println("\nJogo com menor custo do: " + menorCusto);

    }
}
