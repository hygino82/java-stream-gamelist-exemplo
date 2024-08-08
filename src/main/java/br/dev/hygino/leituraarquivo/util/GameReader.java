package br.dev.hygino.leituraarquivo.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import br.dev.hygino.leituraarquivo.model.ConsoleType;
import br.dev.hygino.leituraarquivo.model.Game;

public class GameReader {

    private final List<Game> games;

    public GameReader() {
        games = new ArrayList<>();
        readFile();
    }

    private void readFile() {
        String filePath = "games.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Divide a linha por ponto e vírgula
                String[] values = line.split(";");
                final String name = values[0];
                final ConsoleType console = ConsoleType.valueOf(values[1]);
                final String brand = values[2];
                final LocalDate releaseDate = LocalDate.parse(values[3]);
                final double price = Double.parseDouble(values[4]);
                games.add(new Game(name, console, brand, releaseDate, price));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo!");
        } catch (NumberFormatException nfe) {
            System.out.println("Formato numérico inválido!");
        } catch (DateTimeParseException dtpe) {
            System.out.println("Formato de data inválida!");
        }
    }

    public void mostrarValores() {
        games.forEach(System.out::println);
    }

    public List<String> findGamesByConsole(ConsoleType console) {
        return games.stream()
                .filter(game -> game.console() == console)
                .map(Game::name)
                .toList();
    }

    public Map<ConsoleType, List<String>> groupGamesByConsole() {
        return games.stream().collect(Collectors.groupingBy(
                Game::console,
                Collectors.mapping(Game::name, Collectors.toList())
        ));
    }

    public List<Game> findGamesReleasedBetween(LocalDate startDate, LocalDate endDate) {
        return games.stream()
                .filter(game -> !game.releaseDate().isBefore(startDate) && !game.releaseDate().isAfter(endDate))
                .toList();
    }

    public long countGamesReleasedBetween(LocalDate startDate, LocalDate endDate) {
        Predicate<Game> filtro = game -> !game.releaseDate().isBefore(startDate) && !game.releaseDate().isAfter(endDate);
        return games.stream().filter(filtro).count();
    }

    public double countTotalGamePrice() {
        return games.stream().map(Game::price).reduce(0.0, Double::sum);
    }

    public Map<ConsoleType, Double> gameCostByConsole() {
        return games.stream().collect(Collectors.groupingBy(
                Game::console, // Agrupa pelo nome do console
                Collectors.summingDouble(Game::price) // Soma os preços dos jogos em cada grupo
        ));
    }

    public Map<ConsoleType, Double> gameCostByConsole2() {
        return games.stream()
                .collect(Collectors.groupingBy(
                        Game::console,  // Agrupa os jogos por console
                        Collectors.collectingAndThen(
                                Collectors.toList(),  // Coleta os jogos em uma lista
                                list -> list.stream()  // Cria um stream a partir da lista
                                        .sorted((g1, g2) -> g1.price().compareTo(g2.price()))  // Ordena os jogos por preço
                                        .map(Game::price)  // Mapeia para os preços
                                        .reduce(0.0, Double::sum)  // Soma os preços
                        )
                ));
    }

    public Map<Integer, List<Game>> groupGamesByYear() {
        return games.stream().collect(Collectors.groupingBy(game -> game.releaseDate().getYear()));
    }
}
