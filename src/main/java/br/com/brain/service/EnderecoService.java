package br.com.brain.service;

import org.springframework.stereotype.Service;

import br.com.brain.domain.endereco.DadosEndereco;
import br.com.brain.domain.endereco.Endereco;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    public Endereco preencherEnderco(@Valid DadosEndereco dados) {
        var endereco = new Endereco();
        endereco.setLogradouro(dados.logradouro());
        endereco.setBairro(dados.bairro());
        endereco.setCep(dados.cep());
        endereco.setUf(dados.uf());
        endereco.setCidade(dados.cidade());
        endereco.setNumero(dados.numero());
        endereco.setComplemento(dados.complemento());
        return endereco;
    }

    public Endereco atualizarEndereco(Endereco endereco, DadosEndereco dados) {

        if (dados.logradouro() != null) {
            endereco.setLogradouro(dados.logradouro());
        }
        if (dados.bairro() != null) {
            endereco.setBairro(dados.bairro());
        }
        if (dados.cep() != null) {
            endereco.setCep(dados.cep());
        }
        if (dados.uf() != null) {
            endereco.setUf(dados.uf());
        }
        if (dados.cidade() != null) {
            endereco.setCidade(dados.cidade());
        }
        if (dados.numero() != null) {
            endereco.setNumero(dados.numero());
        }
        if (dados.complemento() != null) {
            endereco.setComplemento(dados.complemento());
        }

        return endereco;
    }
}
