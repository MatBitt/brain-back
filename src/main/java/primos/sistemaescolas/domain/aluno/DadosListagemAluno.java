package primos.sistemaescolas.domain.aluno;

import primos.sistemaescolas.domain.endereco.Endereco;

public record DadosListagemAluno(String cpf, String matricula, String nome, String email, Endereco endereco, String tipoSanguineo) {

    public DadosListagemAluno(Aluno aluno) {
        this(aluno.getCpf(), aluno.getMatricula(), aluno.getNome(), aluno.getEmail(), aluno.getEndereco(), aluno.getTipoSanguineo());
    }

}
