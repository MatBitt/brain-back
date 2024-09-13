package br.com.brain.domain.aluno;

import br.com.brain.domain.endereco.Endereco;

public record DadosListagemAluno(String cpf, String matricula, String nome, String email, Endereco endereco, String tipoSanguineo) {

    public DadosListagemAluno(Aluno aluno) {
        this(aluno.getCpf(), aluno.getMatricula(), aluno.getNome(), aluno.getEmail(), aluno.getEndereco(), aluno.getTipoSanguineo());
    }

}
