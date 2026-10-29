package br.com.estudo.filmesapi;

import java.util.*;
import java.math.BigDecimal;
public interface FilmeModel {
    Filme criar(Filme entrada);
    List<Filme> listar(String genero, String titulo);
    Optional<Filme> buscar(Long id);
    Optional<Filme> atualizar(Long id, Filme entrada);
    boolean excluir(Long id);
}
