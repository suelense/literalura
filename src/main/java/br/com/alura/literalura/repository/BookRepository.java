package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Authors;
import br.com.alura.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String bookName);

    @Query("SELECT a FROM Book b JOIN b.authors a ORDER BY name")
    List<Authors> findAllAuthors();

    @Query(value = "SELECT * FROM public.books ORDER BY languages", nativeQuery = true)
    List<Book> findAllBooksOrderByLanguages();

    List<Book> findTop5ByOrderByDownloadCountDesc();
}
