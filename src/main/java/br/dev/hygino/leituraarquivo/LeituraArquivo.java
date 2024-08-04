package br.dev.hygino.leituraarquivo;

import br.dev.hygino.leituraarquivo.model.ConsoleType;
import br.dev.hygino.leituraarquivo.util.GameReader;

public class LeituraArquivo {

    public static void main(String[] args) {
        GameReader reader = new GameReader();
        
        reader.mostrarValores();
        
        ConsoleType console=ConsoleType.MEGA_DRIVE;
        final var consoleGames=reader.findGamesByConsole(console);
        
    }
}
