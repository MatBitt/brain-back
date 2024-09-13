package primos.sistemaescolas.domain.professor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import primos.sistemaescolas.domain.endereco.DadosEndereco;

import java.sql.Date;

public record DadosCadastroProfessor(

        @NotBlank @Pattern(regexp = "\\d{11}") String cpf,
        String rg,
        @NotBlank String nome,
        String nomeSocial,
        @NotBlank @Email String email,
        @NotNull Date dataDeNascimento,
        @NotNull @Valid DadosEndereco endereco,
        String genero,
        String corRaca,
        String cidadeNaturalidade,
        String carteiraDeTrabalho
) {
}
