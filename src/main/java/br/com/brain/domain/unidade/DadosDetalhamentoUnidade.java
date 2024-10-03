package br.com.brain.domain.unidade;

public record DadosDetalhamentoUnidade(String nome) {

    public DadosDetalhamentoUnidade(Unidade unidade) {
        this(unidade.getNome());
    }
}
