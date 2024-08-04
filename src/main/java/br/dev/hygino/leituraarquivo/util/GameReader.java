package br.dev.hygino.leituraarquivo.util;

import br.dev.hygino.leituraarquivo.model.ConsoleType;
import br.dev.hygino.leituraarquivo.model.Game;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
}
