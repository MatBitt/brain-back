package br.com.brain.domain.unidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUnidade(
        @NotNull Long id,
        @NotBlank String nome) {
}
