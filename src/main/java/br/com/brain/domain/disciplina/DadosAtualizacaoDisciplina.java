package br.com.brain.domain.disciplina;

import br.com.brain.domain.grupo.GrupoDisciplina;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoDisciplina(
        @NotNull Long id,
        String nome,
        String unidade,
        String serie,
        String cargaHoraria,
        GrupoDisciplina grupo) {
}
