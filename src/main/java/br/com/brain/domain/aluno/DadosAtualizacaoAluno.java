package br.com.brain.domain.aluno;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

import br.com.brain.domain.endereco.DadosEndereco;

public record DadosAtualizacaoAluno(

        String matricula,
        @NotBlank
        String nome,

        Date dataDeNascimento,
        @Email
        String email,
        @NotNull @Valid DadosEndereco endereco) {
}
