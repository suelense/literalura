package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    private String[] languages;
    private Integer downloadCount;
    private String image;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Authors> authors = new ArrayList<>();

    public Book() {}

    public Book(BookData bookData) {
        this.title = bookData.title();
        this.languages = bookData.languages();
        this.downloadCount = bookData.download_count();
        List<AuthorsData> authorsData = bookData.authors().stream().toList();
        authorsData.forEach(a -> authors.add(new Authors(a,this)));
        this.image = bookData.formats().get("image/jpeg");
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

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
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
                    ", Autores= " + authorsName()+
                    " Idioma= " + languages[0] +
                    ", Total de Downloads= " + downloadCount;
    }

    private String authorsName() {
        StringBuilder authorsName = new StringBuilder();
        for (Authors a : authors) {
            authorsName.append(a.getName()).append(", ");
        }
        return authorsName.toString();
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
