package br.com.brain.domain.professor;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

import br.com.brain.domain.endereco.Endereco;

@Entity
@Table(name = "professores")
@Data
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
}
