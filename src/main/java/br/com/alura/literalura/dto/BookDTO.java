package br.com.alura.literalura.dto;

import br.com.alura.literalura.model.Authors;

import java.util.List;

public record BookDTO(Long id,
                      String title,
                      String[] languages,
                      Integer download_count,
                      String image) {
}
