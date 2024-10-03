package br.com.brain.domain.serie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoSerie(
        @NotNull Long id,
        @NotBlank String nome) {
}
