package br.com.brain.domain.disciplina;

import br.com.brain.domain.grupo.GrupoDisciplina;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroDisciplina(

        @NotBlank String unidade,
        @NotBlank String serie,
        @NotBlank String nome,
        @NotBlank String cargaHoraria,
        @NotNull GrupoDisciplina grupo) {
}
