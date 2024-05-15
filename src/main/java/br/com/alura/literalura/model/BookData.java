package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData (@JsonAlias("title") String title,
                        @JsonAlias("authors") List<AuthorsData> authors,
                        @JsonAlias("languages") String[] languages,
                        @JsonAlias("download_count") Integer download_count) {
}
