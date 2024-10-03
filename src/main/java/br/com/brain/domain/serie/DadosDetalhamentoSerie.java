package br.com.brain.domain.serie;

public record DadosDetalhamentoSerie(String nome) {

    public DadosDetalhamentoSerie(Serie serie) {
        this(serie.getNome());
    }
}
