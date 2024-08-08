package br.dev.hygino.leituraarquivo.collections;

import java.util.List;
import static java.util.stream.Collectors.*;
import java.util.Map;
import java.util.stream.Collectors;

import br.dev.hygino.leituraarquivo.model.Book;

public class ColecaoLivros {

        public ColecaoLivros() {
        }

        private final List<Book> livros = List.of(
                        // Romantismo
                        new Book("O Guarani", "José de Alencar", "B. L. Garnier", 1857, 546),
                        new Book("Iracema", "José de Alencar", "B. L. Garnier", 1865, 194),

                        new Book("A Moreninha", "Joaquim Manuel de Macedo", "Tipografia Nacional", 1844, 192),
                        new Book("Senhora", "José de Alencar", "B. L. Garnier", 1875, 272),

                        // Realismo
                        new Book("Dom Casmurro", "Machado de Assis", "Garnier", 1899, 256),
                        new Book("Memórias Póstumas de Brás Cubas", "Machado de Assis", "Tipografia Nacional", 1881,
                                        218),

                        new Book("O Primo Basílio", "Eça de Queirós", "Typographia Castro Irmão", 1878, 492),
                        new Book("O Mulato", "Aluísio Azevedo", "Livraria Garnier", 1881, 256),

                        // Modernismo
                        new Book("Macunaíma", "Mário de Andrade", "Livraria do Globo", 1928, 189),
                        new Book("Pauliceia Desvairada", "Mário de Andrade", "Casa Editora Monteiro Lobato", 1922, 70),

                        new Book("Vidas Secas", "Graciliano Ramos", "José Olympio", 1938, 175),
                        new Book("São Bernardo", "Graciliano Ramos", "José Olympio", 1934, 213),

                        new Book("Capitães da Areia", "Jorge Amado", "José Olympio", 1937, 280),
                        new Book("Gabriela, Cravo e Canela", "Jorge Amado", "Livraria Martins", 1958, 351),

                        new Book("Memórias Sentimentais de João Miramar", "Oswald de Andrade",
                                        "Livraria Monteiro Lobato", 1937,
                                        180),
                        new Book("Serafim Ponte Grande", "Oswald de Andrade", "Livraria Martins Editora", 1933, 152),

                        // Contemporânea
                        new Book("A Hora da Estrela", "Clarice Lispector", "Rocco", 1977, 96),
                        new Book("Laços de Família", "Clarice Lispector", "Francisco Alves", 1960, 188),

                        new Book("O Encontro Marcado", "Fernando Sabino", "Ed. do Autor", 1956, 262),
                        new Book("O Grande Mentecapto", "Fernando Sabino", "Ática", 1979, 296),

                        new Book("Lavoura Arcaica", "Raduan Nassar", "Companhia das Letras", 1975, 204),
                        new Book("Um Copo de Cólera", "Raduan Nassar", "Companhia das Letras", 1978, 88),

                        new Book("O Filho Eterno", "Cristovão Tezza", "Record", 2007, 224),
                        new Book("A Suavidade do Vento", "Cristovão Tezza", "Companhia das Letras", 1991, 144),

                        // Paulo Coelho
                        new Book("O Alquimista", "Paulo Coelho", "Rocco", 1988, 208),
                        new Book("Brida", "Paulo Coelho", "Rocco", 1990, 266));

        public Map<String, List<String>> agruparLivrosPorAutor() {
                return livros.stream()
                                .collect(Collectors.groupingBy(
                                                Book::author,
                                                Collectors.mapping(Book::title, Collectors.toList())));
        }

        public Map<String, Integer> agruparAutorPorQuantidadePaginasTotal() {
                return livros.stream()
                                .collect(groupingBy(Book::author, summingInt(Book::totalPages)));
        }
}
