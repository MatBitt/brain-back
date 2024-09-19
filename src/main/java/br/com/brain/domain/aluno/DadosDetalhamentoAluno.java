package br.com.brain.domain.aluno;

import java.sql.Date;

import br.com.brain.domain.endereco.Endereco;

public record DadosDetalhamentoAluno(String cpf, String rg, String matricula, String nome, String nomeSocial,
        Date dataDeNascimento, String email, Endereco endereco) {

    public DadosDetalhamentoAluno(Aluno aluno) {
        this(
                aluno.getCpf(),
                aluno.getRg(),
                aluno.getMatricula(),
                aluno.getNome(),
                aluno.getNomeSocial(),
                aluno.getDataDeNascimento(),
                aluno.getEmail(),
                aluno.getEndereco());
    }
}
