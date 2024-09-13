package br.com.brain.domain.professor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

import br.com.brain.domain.endereco.Endereco;

@Entity
@Table(name = "professores")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cpf")
public class Professor {

    @Id
    private String cpf;
    private String rg;
    private String matricula;
    private String nome;
    @Column(name = "nome_social")
    private String nomeSocial;
    private String email;
    @Column(name = "email_profissional")
    private String emailProfissional;
    @Column(name = "data_de_nascimento")
    private Date dataDeNascimento;
    @Embedded
    private Endereco endereco;
    private String genero;
    @Column(name = "cor_raca")
    private String corRaca;
    @Column(name = "cidade_naturalidade")
    private String cidadeNaturalidade;
    @Column(name = "carteira_de_trabalho")
    private String carteiraDeTrabalho;

    public Professor (DadosCadastroProfessor dados) {
        this.cpf = dados.cpf();
        this.nome = dados.nome();
        this.nomeSocial = dados.nomeSocial();
        this.email = dados.email();
        this.emailProfissional = dados.cpf() + "@gmail.com";
        this.dataDeNascimento = dados.dataDeNascimento();
        this.endereco = new Endereco(dados.endereco());
        this.genero = dados.genero();
        this.corRaca = dados.corRaca();
        this.cidadeNaturalidade = dados.cidadeNaturalidade();
        this.rg = dados.rg();
        this.matricula = "M" + dados.cpf();
        this.carteiraDeTrabalho = dados.carteiraDeTrabalho();
    }

    public void atualizarInformacoes(DadosAtualizacaoProfessor dados) {
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
