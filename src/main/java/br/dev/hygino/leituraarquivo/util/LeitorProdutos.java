package br.dev.hygino.leituraarquivo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.dev.hygino.leituraarquivo.model.Produto;

public class LeitorProdutos {
    private final List<Produto> produtos;

    public LeitorProdutos() {
        this.produtos = new ArrayList<>();
        lerListaProdutos();
    }

    private void lerListaProdutos() {
        final String arquivoCSV = "produtos.csv";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (Scanner scanner = new Scanner(new File(arquivoCSV))) {
            // Ignora a primeira linha (cabeçalho)
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] campos = linha.split(";");

                String nome = campos[0];
                String marca = campos[1];
                double preco = Double.parseDouble(campos[2]);
                String cidade = campos[3];
                String estado = campos[4];
                LocalDate dataFabricacao = LocalDate.parse(campos[5], formatter);

                Produto produto = new Produto(nome, marca, preco, cidade, estado, dataFabricacao);
                produtos.add(produto);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (NumberFormatException nfe) {
            System.out.println("Formato numérico inválido");
        }
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Map<String, Long> quantidadeProdutosPorEstado() {
        return produtos.stream()
                .collect(Collectors.groupingBy(Produto::estado, Collectors.counting()));
    }
}