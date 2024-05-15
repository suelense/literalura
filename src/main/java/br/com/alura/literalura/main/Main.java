package br.com.alura.literalura.main;

import br.com.alura.literalura.model.Data;
import br.com.alura.literalura.repository.BookRepository;
import br.com.alura.literalura.service.ConvertData;
import br.com.alura.literalura.service.GetAPI;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private final String ADDRESS = "https://gutendex.com/books/?search=poir";
    private BookRepository repository;
    ConvertData converter = new ConvertData();
    GetAPI getAPI = new GetAPI();
    List<Data> data = new ArrayList<Data>();

    public Main(BookRepository repository) {
        this.repository = repository;
    }

    public void showMenu() {
        System.out.println("""
                Escolha a opção desejada""");
        searchBook();
    }

    public void searchBook() {
        var json = getAPI.getData(ADDRESS);
        var mappedJson = converter.convertData(json, Data.class);
        data.add(mappedJson);
        System.out.println(data);
//        repository.save(mappedJson);
//        String varr = mappedJson.getResults().toString();
//        System.out.println(varr);
//        date.forEach(System.out::println);
//        date.forEach(d -> d.setResults(mappedJson));
//
//        var teste = objectMapper.writeValueAsString(lista);


    }
}
