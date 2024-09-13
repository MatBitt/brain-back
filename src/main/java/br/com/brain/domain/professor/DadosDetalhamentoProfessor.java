package br.com.brain.domain.professor;

import java.sql.Date;

import br.com.brain.domain.endereco.Endereco;

public record DadosDetalhamentoProfessor(String cpf, String matricula, String nome, Date dataDeNascimento, String email, Endereco endereco) {

    public DadosDetalhamentoProfessor(Professor professor) {
        this(professor.getCpf(), professor.getMatricula(), professor.getNome(), professor.getDataDeNascimento(), professor.getEmail(), professor.getEndereco());
    }
}
