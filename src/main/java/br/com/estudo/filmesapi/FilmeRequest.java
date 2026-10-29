package br.com.estudo.filmesapi;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;

// DTO de entrada; ID é gerado no servidor.
public record FilmeRequest(
    @NotBlank(message="titulo é obrigatório") @Size(max=120) String titulo,
    @NotBlank(message="genero é obrigatório") @Size(max=120) String genero,
    @NotNull @Min(1888) @Max(2100) Integer ano
) {}
