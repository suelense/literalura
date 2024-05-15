package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String[] languages;
    private Integer download_count;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Authors> authors = new ArrayList<>();

    public Book() {
    }

    public Book(BookData bookData) {
        this.title = bookData.title();
        this.languages = bookData.languages();
        this.download_count = bookData.download_count();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getLanguages() {
        return languages;
    }

    public Integer getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Integer download_count) {
        this.download_count = download_count;
    }

    public List<Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Authors> authors) {
        authors.forEach(a -> a.setBook(this));
        this.authors = authors;
    }

    @Override
    public String toString() {
    return
            "Titulo= " + title +
                    ", Autores= '" + authors +
                    ", Idioma= " + languages +
                    ", Total de Downloads= " + download_count + '\n';
    }
}
