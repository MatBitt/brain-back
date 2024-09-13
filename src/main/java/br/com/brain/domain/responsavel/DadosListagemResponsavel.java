package br.com.brain.domain.responsavel;

import br.com.brain.domain.endereco.Endereco;

public record DadosListagemResponsavel(String nome, String email, Endereco endereco, String rg) {

    public DadosListagemResponsavel(Responsavel responsavel) {
        this(responsavel.getNome(), responsavel.getEmail(), responsavel.getEndereco(), responsavel.getRg());
    }

}
