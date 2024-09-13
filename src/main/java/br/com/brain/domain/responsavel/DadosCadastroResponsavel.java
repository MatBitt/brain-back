package br.com.brain.domain.responsavel;

import br.com.brain.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroResponsavel(

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotBlank
        String nome,
        @Email
        String email,
        @NotNull @Valid
        DadosEndereco endereco,
        @NotBlank
        String rg) {
}
