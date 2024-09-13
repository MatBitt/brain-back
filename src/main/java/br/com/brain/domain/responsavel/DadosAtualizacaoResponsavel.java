package br.com.brain.domain.responsavel;

import br.com.brain.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoResponsavel(
        @NotBlank
        String nome,
        @NotBlank

        String cpf,
        @Email
        String email,
        @NotNull @Valid
        DadosEndereco endereco) {
}
