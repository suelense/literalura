package br.com.alura.literalura.main;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.BookRepository;
import br.com.alura.literalura.service.ConvertData;
import br.com.alura.literalura.service.GetAPI;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private BookRepository repository;
    private ConvertData converter = new ConvertData();
    private GetAPI getAPI = new GetAPI();
    private Scanner scanner = new Scanner(System.in);
    List<Book> book = new ArrayList<>();

    private String bookName;

    public Main(BookRepository repository) {
        this.repository = repository;
    }

    public void showMenu() {
        int option = -1;
        while (option != 0) {
            System.out.println("""
                Escolha a opção desejada:
                1 - Buscar livro pelo nome
                2 - Lista de todos os livros pesquisados
                3 - Lista de autores
                4 - Lista de autores vivos em determinado ano
                5 - Lista de livros por idioma
                6 - Top 5 Livros mais baixados
                
                0 - Sair""");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    searchBook();
                    break;
                case 2:
                    listAllBooks();
                    break;
                case 3:
                    listALlAuthors();
                    break;
                case 4:
                    System.out.println("Digite o ano desejado");
                    Integer year = scanner.nextInt();
                    scanner.nextLine();
                    livingAuthorsInAYear(year);
                    break;
                case 5:
                    List<Book> book = repository.findAllBooksOrderByLanguages();
                    book.forEach(b -> System.out.println("Idioma = " + Arrays.toString(b.getLanguages()) + ", Título = " + b.getTitle()));

                    break;
                case 6:
                    top5Books();
                    break;
                case 0:
                    System.out.println("Sistema encerrado com sucesso");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private ResultsData getBookData(String bookName) {
        String ADDRESS = "https://gutendex.com/books/?search=";
        var json = getAPI.getData(ADDRESS + bookName.toLowerCase().replace(" ", "%20"));
        return converter.convertData(json, ResultsData.class);
    }

    private void searchBook() {
        System.out.println("Digite o nome do livro: ");
        String bookName = scanner.nextLine();

        ResultsData resultsData = getBookData(bookName);
        List<BookData> bookData = resultsData.results().stream().toList();
        bookData.forEach(b -> book.add(new Book(b)));
        try {
            book.forEach(b -> repository.save(b));
        } catch (Exception e) {
        }
        System.out.println(repository.findByTitleContainingIgnoreCase(bookName));
    }

    private void listAllBooks() {
        List<Book> books = repository.findAll();
        books.forEach(System.out::println);
    }

    private void listALlAuthors() {
        List<Authors> listAuthors = repository.findAllAuthors();
        listAuthors.forEach(System.out::println);
    }

    private void livingAuthorsInAYear(Integer year) {
        List<Authors> authors = repository.findAllAuthors();

        authors.forEach(a -> {if (a.getDeath_year() != null || a.getBirth_year() != null)
            if (year >= a.getBirth_year() && year <= a.getDeath_year())
                System.out.println(a);
        });
    }

    private void top5Books() {
        List<Book> top5Books = repository.findTop5ByOrderByDownloadCountDesc();
        top5Books.forEach(System.out::println);
    }

}
