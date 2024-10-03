package br.com.brain.domain.aluno;


import java.sql.Date;

import br.com.brain.domain.endereco.Endereco;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alunos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    private String cpf;
    private String nome;
    @Column(name = "nome_social")
    private String nomeSocial;
    private String email;
    @Column(name = "email_escolar")
    private String emailEscolar;
    @Column(name = "data_de_nascimento")
    private Date dataDeNascimento;
    @Embedded
    private Endereco endereco;
    private String genero;
    @Column(name = "cor_raca")
    private String corRaca;
    @Column(name = "cidade_naturalidade")
    private String cidadeNaturalidade;
    private String rg;
    private String matricula;
    @Column(name = "tipo_sanguineo")
    private String tipoSanguineo;

    public Aluno (DadosCadastroAluno dados) {
        this.cpf = dados.cpf();
        this.nome = dados.nome();
        this.nomeSocial = dados.nomeSocial();
        this.email = dados.email();
        this.emailEscolar = dados.cpf() + "@gmail.com";
        this.dataDeNascimento = dados.dataDeNascimento();
        this.endereco = new Endereco(dados.endereco());
        this.genero = dados.genero();
        this.corRaca = dados.corRaca();
        this.cidadeNaturalidade = dados.cidadeNaturalidade();
        this.rg = dados.rg();
        this.matricula = "M" + dados.cpf();
        this.tipoSanguineo = dados.tipoSanguineo();
    }

    public void atualizarInformacoes(DadosAtualizacaoAluno dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.dataDeNascimento() != null) {
            this.dataDeNascimento = dados.dataDeNascimento();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.matricula() != null) {
            this.matricula = dados.matricula();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }
}
