package br.com.alura.literalura.controler;

import br.com.alura.literalura.dto.BookDTO;
import br.com.alura.literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookControler {
    @Autowired
    private BookService service;

    @GetMapping("/books")
    public List<BookDTO> getBook() {
        return service.getAllBooks();
    }
}
