package primos.sistemaescolas.domain.aluno;

import primos.sistemaescolas.domain.endereco.Endereco;

import java.sql.Date;

public record DadosDetalhamentoAluno(String cpf, String matricula, String nome, Date dataDeNascimento, String email, Endereco endereco) {

    public DadosDetalhamentoAluno(Aluno aluno) {
        this(aluno.getCpf(), aluno.getMatricula(), aluno.getNome(), aluno.getDataDeNascimento(), aluno.getEmail(), aluno.getEndereco());
    }
}
