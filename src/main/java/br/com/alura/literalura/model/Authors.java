package br.com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "authors")
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birth_year;
    private Integer death_year;

    @ManyToOne
    private Book book;

    public Authors () {}

    public Authors( AuthorsData authorsData, Book book){
        String[] author = authorsData.name().split(",");
        this.name = author[1] + " " + author[0];
        this.birth_year = authorsData.birth_year();
        this.death_year = authorsData.death_year();
        this.book = book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public Integer getDeath_year() {
        return death_year;
    }

    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return
               name;
    }
}