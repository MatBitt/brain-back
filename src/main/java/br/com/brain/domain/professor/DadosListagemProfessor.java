package br.com.brain.domain.professor;

import br.com.brain.domain.endereco.Endereco;

public record DadosListagemProfessor(String cpf, String matricula, String nome, String nomeSocial, String email,
        String emailProfissional, Endereco endereco, String rg, String carteiraDeTrabalho) {

    public DadosListagemProfessor(Professor professor) {
        this(professor.getCpf(), professor.getMatricula(), professor.getNome(), professor.getNomeSocial(),
                professor.getEmail(), professor.getEmailProfissional(), professor.getEndereco(), professor.getRg(),
                professor.getCarteiraDeTrabalho());
    }

}
