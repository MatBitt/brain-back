package br.com.brain.domain.disciplina;

import br.com.brain.domain.grupo.GrupoDisciplina;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "disciplinas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String unidade;
    private String serie;
    private String nome;
    @Column(name = "carga_horaria")
    private String cargaHoraria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_id", referencedColumnName = "id")
    private GrupoDisciplina grupo;

    public Disciplina(DadosCadastroDisciplina dados) {
        this.nome = dados.nome();
        this.cargaHoraria = dados.cargaHoraria();
        this.grupo = dados.grupo();
        this.serie = dados.serie();
        this.unidade = dados.unidade();
    }

    public void atualizarInformacoes(DadosAtualizacaoDisciplina dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.unidade() != null) {
            this.unidade = dados.unidade();
        }
        if (dados.serie() != null) {
            this.serie = dados.serie();
        }
        if (dados.cargaHoraria() != null) {
            this.cargaHoraria = dados.cargaHoraria();
        }
        if (dados.grupo() != null) {
            this.grupo = dados.grupo();
        }
    }
}
