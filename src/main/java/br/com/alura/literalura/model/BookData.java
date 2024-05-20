package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData (@JsonAlias("title") String title,
                        @JsonAlias("authors") List<AuthorsData> authors,
                        @JsonAlias("download_count") Integer download_count,
                        @JsonAlias("languages") String[] languages ) {
}
