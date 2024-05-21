package br.com.alura.literalura.service;

import br.com.alura.literalura.dto.BookDTO;
import br.com.alura.literalura.model.Book;
import br.com.alura.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public List<BookDTO> getAllBooks() {
        return convertData(repository.findAll());
    }

    private List<BookDTO> convertData(List<Book> books) {
        return books.stream()
                .map(b -> new BookDTO(b.getId(),
                        b.getTitle(),
                        b.getLanguages(),
                        b.getDownloadCount(),
                        b.getImage()))
                .collect(Collectors.toList());
    }
}
