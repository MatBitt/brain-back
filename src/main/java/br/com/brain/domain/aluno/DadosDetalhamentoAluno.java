package br.com.brain.domain.aluno;

import java.sql.Date;

import br.com.brain.domain.endereco.Endereco;

public record DadosDetalhamentoAluno(String cpf, String matricula, String nome, Date dataDeNascimento, String email, Endereco endereco) {

    public DadosDetalhamentoAluno(Aluno aluno) {
        this(aluno.getCpf(), aluno.getMatricula(), aluno.getNome(), aluno.getDataDeNascimento(), aluno.getEmail(), aluno.getEndereco());
    }
}
