package br.com.brain.domain.grupo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grupos_disciplinas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GrupoDisciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String area;

    public GrupoDisciplina (DadosCadastroGrupoDisciplina dados) {
        this.nome = dados.nome();
        this.area = dados.area();
    }

    public void atualizarInformacoes(DadosAtualizacaoGrupoDisciplina dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.area() != null) {
            this.area = dados.area();
        }
    }
}
