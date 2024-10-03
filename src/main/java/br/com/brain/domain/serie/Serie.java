package br.com.brain.domain.serie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "series")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Serie(DadosCadastroSerie dados) {
        this.nome = dados.nome();
    }

    public void atualizarInformacoes(DadosAtualizacaoSerie dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
    }
}
