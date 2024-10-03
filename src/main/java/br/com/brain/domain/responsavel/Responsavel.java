package br.com.brain.domain.responsavel;

import br.com.brain.domain.endereco.Endereco;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "responsaveis")
@Data
public class Responsavel {

    @Id
    private String cpf;
    private String nome;
    private String email;
    private Endereco endereco;
    private String rg;
}
