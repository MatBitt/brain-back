package br.com.brain.domain.grupo;

public record DadosListagemGrupoDisciplina(String nome, String area) {

    public DadosListagemGrupoDisciplina(GrupoDisciplina grupoDisciplina) {
        this(grupoDisciplina.getNome(), grupoDisciplina.getArea());
    }

}
