package br.com.estudo.filmesapi;


public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(Long id) {
        super("Recurso " + id + " não encontrado");
    }
}
