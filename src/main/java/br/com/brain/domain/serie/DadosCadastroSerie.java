package br.com.brain.domain.serie;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroSerie(
        @NotBlank String nome) {
}
