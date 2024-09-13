package primos.sistemaescolas.domain.responsavel;

import primos.sistemaescolas.domain.endereco.Endereco;

public record DadosDetalhamentoResponsavel(String nome, String email, Endereco endereco) {

    public DadosDetalhamentoResponsavel(Responsavel responsavel) {
        this(responsavel.getNome(), responsavel.getEmail(), responsavel.getEndereco());
    }
}
