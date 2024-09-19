package br.com.brain.domain.aluno;

import br.com.brain.domain.endereco.Endereco;

public record DadosListagemAluno(String cpf, String rg, String matricula, String nome, String nomeSocial, String email,
        String emailEscolar, Endereco endereco, String tipoSanguineo) {

    public DadosListagemAluno(Aluno aluno) {
        this(aluno.getCpf(), aluno.getRg(), aluno.getMatricula(), aluno.getNome(), aluno.getNomeSocial(),
                aluno.getEmail(), aluno.getEmailEscolar(), aluno.getEndereco(), aluno.getTipoSanguineo());
    }

}
