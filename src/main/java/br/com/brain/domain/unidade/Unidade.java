package br.com.brain.domain.unidade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "unidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Unidade(DadosCadastroUnidade dados) {
        this.nome = dados.nome();
    }

    public void atualizarInformacoes(DadosAtualizacaoUnidade dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
    }
}
