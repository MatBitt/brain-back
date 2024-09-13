package br.com.brain.domain.responsavel;

import br.com.brain.domain.endereco.Endereco;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "responsaveis")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Responsavel {

    @Id
    private String cpf;
    private String nome;
    private String email;
    private Endereco endereco;
    private String rg;

    public Responsavel (DadosCadastroResponsavel dados) {
        this.cpf = dados.cpf();
        this.nome = dados.nome();
        this.email = dados.email();
        this.endereco = new Endereco(dados.endereco());
        this.rg = dados.rg();
    }

    public void atualizarInformacoes(DadosAtualizacaoResponsavel dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }

    }
}
