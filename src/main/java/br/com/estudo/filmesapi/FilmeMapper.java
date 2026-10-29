package br.com.estudo.filmesapi;

public final class FilmeMapper {
    private FilmeMapper() {}
    public static Filme toEntity(FilmeRequest r, Long id) { return new Filme(id, r.titulo().strip(), r.genero().strip(), r.ano()); }
    public static FilmeResponse toResponse(Filme item) { return new FilmeResponse(item.getId(), item.getTitulo(), item.getGenero(), item.getAno()); }
}
