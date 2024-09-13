package primos.sistemaescolas.domain.responsavel;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import primos.sistemaescolas.domain.endereco.DadosEndereco;

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
