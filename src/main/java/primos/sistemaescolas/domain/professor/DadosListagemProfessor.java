package primos.sistemaescolas.domain.professor;

import primos.sistemaescolas.domain.endereco.Endereco;

public record DadosListagemProfessor(String cpf, String matricula, String nome, String email, Endereco endereco, String rg) {

    public DadosListagemProfessor(Professor professor) {
        this(professor.getCpf(), professor.getMatricula(), professor.getNome(), professor.getEmail(), professor.getEndereco(), professor.getRg());
    }

}
