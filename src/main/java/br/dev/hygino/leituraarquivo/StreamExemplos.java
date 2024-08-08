package br.dev.hygino.leituraarquivo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import br.dev.hygino.leituraarquivo.collections.ColecaoLivros;

public class StreamExemplos {
    public static void main(String[] args) {

        ColecaoLivros colecao = new ColecaoLivros();

        final var agruparPorAutor = colecao.agruparLivrosPorAutor();
        agruparPorAutor.forEach((autor, livros) -> {
            System.out.println(autor);
            livros.forEach(System.out::println);
            System.out.println("--------------------------------");
        });

        final var agruparPorTotalPaginas = colecao.agruparAutorPorQuantidadePaginasTotal();
        agruparPorTotalPaginas.forEach((a, p) -> System.out.printf("%s: %d\n", a, p));
    }

    static void agruparNumeros() {
        List<Integer> inteiros = IntStream.rangeClosed(1, 15).boxed().toList();
        Map<Boolean, List<Integer>> mapa = inteiros.stream()
                .collect(Collectors.groupingBy(x -> x % 2 == 0));

        mapa.forEach((k, v) -> {
            System.out.println(k ? "Pares" : "Impares");
            v.forEach(System.out::println);
        });
    }
}
