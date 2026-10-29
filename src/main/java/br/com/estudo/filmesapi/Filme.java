package br.com.estudo.filmesapi;

import java.math.BigDecimal;

public class Filme {
    private final Long id;
    private final String titulo;
    private final String genero;
    private final Integer ano;
    public Filme(Long id, String titulo, String genero, Integer ano) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.ano = ano;
    }
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getGenero() { return genero; }
    public Integer getAno() { return ano; }
}
