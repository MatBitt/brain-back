package br.com.brain.domain.responsavel;

import br.com.brain.domain.endereco.Endereco;

public record DadosDetalhamentoResponsavel(String nome, String email, Endereco endereco) {

    public DadosDetalhamentoResponsavel(Responsavel responsavel) {
        this(responsavel.getNome(), responsavel.getEmail(), responsavel.getEndereco());
    }
}
