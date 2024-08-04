package br.dev.hygino.leituraarquivo;

import br.dev.hygino.leituraarquivo.model.ConsoleType;
import br.dev.hygino.leituraarquivo.util.GameReader;

public class LeituraArquivo {

    public static void main(String[] args) {
        GameReader reader = new GameReader();

        reader.mostrarValores();

        ConsoleType consoleType = ConsoleType.MEGA_DRIVE;
        final var consoleGames = reader.findGamesByConsole(consoleType);
        System.out.printf("");
        consoleGames.forEach(System.out::println);

        System.out.println("Jogos agrupados por console");
        reader.groupGamesByConsole().forEach((console, games) -> {
            System.out.println(console);
            games.stream()
                    .sorted()
                    .forEach(System.out::println);
            System.out.println();
        });

    }
}
