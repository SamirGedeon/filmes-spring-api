package br.com.estudo.filmesapi;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/filmes", produces="application/json")
public class FilmeController {
    private final FilmeModel model;
    public FilmeController(FilmeModel model) { this.model = model; }
    @PostMapping(consumes="application/json")
    public ResponseEntity<FilmeResponse> criar(@Valid @RequestBody FilmeRequest request) {
        Filme criado = model.criar(FilmeMapper.toEntity(request, null));
        return ResponseEntity.created(URI.create("/filmes/"+criado.getId())).body(FilmeMapper.toResponse(criado));
    }
    @GetMapping
    public List<FilmeResponse> listar(@RequestParam(name="genero", required=false) String genero,
            @RequestParam(name="titulo", required=false) String titulo) { return model.listar(genero, titulo).stream().map(FilmeMapper::toResponse).toList(); }
    @GetMapping("/{id}")
    public FilmeResponse buscar(@PathVariable("id") Long id) { return FilmeMapper.toResponse(model.buscar(id).orElseThrow(() -> new RecursoNaoEncontradoException(id))); }
    @PutMapping(value="/{id}", consumes="application/json")
    public FilmeResponse atualizar(@PathVariable("id") Long id, @Valid @RequestBody FilmeRequest request) {
        return FilmeMapper.toResponse(model.atualizar(id, FilmeMapper.toEntity(request,id)).orElseThrow(() -> new RecursoNaoEncontradoException(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        if (!model.excluir(id)) throw new RecursoNaoEncontradoException(id);
        return ResponseEntity.noContent().build();
    }
    
}
