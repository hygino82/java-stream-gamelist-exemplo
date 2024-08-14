package br.dev.hygino.solid;

import java.util.List;

public class InversaoDeDependencia {
    public static void main(String[] args) {
        // TrocarJogo trocarJogo = new TrocarJogo(new DiscoJogo());
        // trocarJogo.getTrocarJogo().mudarJogo();
        List<ITrocarJogo> trocarJogos = List.of(
                new CartuchoJogo(),
                new DiscoJogo(),
                new DownloadJogo());

        trocarJogos.forEach(ITrocarJogo::mudarJogo);
    }
}

interface ITrocarJogo {
    void mudarJogo();
}

class CartuchoJogo implements ITrocarJogo {

    @Override
    public void mudarJogo() {
        System.out.println("Remova o cartucho e assopre antes de colocar no console!");
    }

}

class DownloadJogo implements ITrocarJogo {

    @Override
    public void mudarJogo() {
        System.out.println("Pressione o botão de menu do console para efetuar a troca de jogo!");
    }
}

class DiscoJogo implements ITrocarJogo {

    @Override
    public void mudarJogo() {
        System.out.println("Verrifique se o disco está sem aranhões antes de inserir");
    }

}

class TrocarJogo {
    private final ITrocarJogo trocarJogo;

    public TrocarJogo(ITrocarJogo trocarJogo) {
        this.trocarJogo = trocarJogo;
    }

    public ITrocarJogo getTrocarJogo() {
        return trocarJogo;
    }
}