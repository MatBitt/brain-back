package br.com.brain.domain.aluno;


import java.sql.Date;

import br.com.brain.domain.endereco.Endereco;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "alunos")
@Data
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
}
