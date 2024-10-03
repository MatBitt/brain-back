package br.com.brain.domain.serie;

public record DadosListagemSerie(String nome) {

    public DadosListagemSerie(Serie serie) {
        this(serie.getNome());
    }

}
