package br.com.estudo.filmesapi;

import java.util.*;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class ArmazenamentoEmMemoria implements FilmeModel {
    private final Map<Long, Filme> itens = new LinkedHashMap<>();
    private final AtomicLong sequencia = new AtomicLong();
    public synchronized Filme criar(Filme entrada) {
        Long id = sequencia.incrementAndGet();
        Filme criado = new Filme(id, entrada.getTitulo(), entrada.getGenero(), entrada.getAno());
        itens.put(id, criado);
        return criado;
    }
    public synchronized List<Filme> listar(String genero, String titulo) {
        
        return itens.values().stream()
                        .filter(item -> genero == null || genero.isBlank() || item.getGenero().equalsIgnoreCase(genero.strip()))
            .filter(item -> titulo == null || titulo.isBlank() || item.getTitulo().toLowerCase(Locale.ROOT).contains(titulo.strip().toLowerCase(Locale.ROOT)))
            .toList();
    }
    public synchronized Optional<Filme> buscar(Long id) { return Optional.ofNullable(itens.get(id)); }
    public synchronized Optional<Filme> atualizar(Long id, Filme entrada) {
        if (!itens.containsKey(id)) return Optional.empty();
        Filme atualizado = new Filme(id, entrada.getTitulo(), entrada.getGenero(), entrada.getAno());
        itens.put(id, atualizado);
        return Optional.of(atualizado);
    }
    public synchronized boolean excluir(Long id) { return itens.remove(id) != null; }
}
