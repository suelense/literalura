package br.com.alura.literalura.service;

public interface IConvertData {
    <T> T convertData(String json, Class<T> classe);
}
