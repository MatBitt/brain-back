package br.com.brain.domain.grupo;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroGrupoDisciplina(

        @NotBlank String nome,
        @NotBlank String area) {
}
