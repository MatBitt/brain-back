package br.com.brain.domain.grupo;

public record DadosDetalhamentoGrupoDisciplina(String nome, String area) {

    public DadosDetalhamentoGrupoDisciplina(GrupoDisciplina grupoDisciplina) {
        this(grupoDisciplina.getNome(), grupoDisciplina.getArea());
    }
}
