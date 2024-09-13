package primos.sistemaescolas.domain.professor;

import primos.sistemaescolas.domain.endereco.Endereco;

import java.sql.Date;

public record DadosDetalhamentoProfessor(String cpf, String matricula, String nome, Date dataDeNascimento, String email, Endereco endereco) {

    public DadosDetalhamentoProfessor(Professor professor) {
        this(professor.getCpf(), professor.getMatricula(), professor.getNome(), professor.getDataDeNascimento(), professor.getEmail(), professor.getEndereco());
    }
}
