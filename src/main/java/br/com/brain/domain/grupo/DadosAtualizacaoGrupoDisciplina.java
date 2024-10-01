package br.com.brain.domain.grupo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoGrupoDisciplina(
        @NotNull Long id,
        @NotBlank String nome,
        @NotBlank String area) {
}
