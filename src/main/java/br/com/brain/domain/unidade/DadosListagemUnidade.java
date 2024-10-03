package br.com.brain.domain.unidade;

public record DadosListagemUnidade(String nome) {

    public DadosListagemUnidade(Unidade unidade) {
        this(unidade.getNome());
    }

}
