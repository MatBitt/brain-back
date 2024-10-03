package br.com.brain.domain.unidade;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUnidade(
        @NotBlank String nome) {
}
