package primos.sistemaescolas.domain.aluno;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import primos.sistemaescolas.domain.endereco.DadosEndereco;

import java.sql.Date;

public record DadosAtualizacaoAluno(

        String cpf,
        @NotBlank
        String matricula,
        @NotBlank
        String nome,

        Date dataDeNascimento,
        @Email
        String email,
        @NotNull @Valid DadosEndereco endereco) {
}
