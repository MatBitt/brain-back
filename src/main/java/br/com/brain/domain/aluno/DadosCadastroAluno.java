package br.com.brain.domain.aluno;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.sql.Date;

import br.com.brain.domain.endereco.DadosEndereco;

public record DadosCadastroAluno(

        @NotBlank @Pattern(regexp = "\\d{11}") String cpf,
        @NotBlank String nome,
        String nomeSocial,
        @NotBlank @Email String email,
        @NotNull Date dataDeNascimento,
        @NotNull @Valid DadosEndereco endereco,
        String genero,

        String corRaca,

        String cidadeNaturalidade,
        String rg,
        String tipoSanguineo
) {
}
