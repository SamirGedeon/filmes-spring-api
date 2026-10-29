package br.com.estudo.filmesapi;

import java.math.BigDecimal;

public record FilmeResponse(Long id, String titulo, String genero, Integer ano) {}
