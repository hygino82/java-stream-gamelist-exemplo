package br.dev.hygino.leituraarquivo;

import java.util.Locale;
import java.util.Map;

import br.dev.hygino.leituraarquivo.util.LeitorProdutos;

public class StreamProdutosExemplo {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        LeitorProdutos leitorProdutos = new LeitorProdutos();
        leitorProdutos.getProdutos().forEach(System.out::println);

        System.out.println("Quantidade de produtos cadastrados por estado");
        final Map<String, Long> quantidadePorEstado = leitorProdutos.quantidadeProdutosPorEstado();
        quantidadePorEstado.forEach((estado, quantidade) -> System.out.printf("%s: %d\n", estado, quantidade));

        System.out.println("\nLista de produtos cadastrados por marca");
        final var produtosPorMarca = leitorProdutos.listaProdutosPorMarca();
        produtosPorMarca.forEach((produto, marcas) -> {
            System.out.println(produto);
            marcas.forEach(System.out::println);
            System.out.println();
        });
    }
}
