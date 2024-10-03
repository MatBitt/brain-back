package br.com.brain.domain.disciplina;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroDisciplina(

        @NotNull Long unidadeId,
        @NotNull Long serieId,
        @NotBlank String nome,
        @NotBlank String cargaHoraria,
        @NotNull Long grupoId) {
}
