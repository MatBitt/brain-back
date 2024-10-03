package br.com.brain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.brain.domain.responsavel.DadosAtualizacaoResponsavel;
import br.com.brain.domain.responsavel.DadosCadastroResponsavel;
import br.com.brain.domain.responsavel.DadosListagemResponsavel;
import br.com.brain.domain.responsavel.Responsavel;
import br.com.brain.domain.responsavel.ResponsavelRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResponsavelService {

    private final ResponsavelRepository repository;
    private final EnderecoService enderecoService;

    @PersistenceContext
    private EntityManager em;

    public void save(Responsavel responsavel) {
        repository.save(responsavel);
    }

    public Responsavel cadastrarResponsavel(@Valid DadosCadastroResponsavel dados) {

        var responsavel = new Responsavel();
        responsavel.setCpf(dados.cpf());
        responsavel.setNome(dados.nome());
        responsavel.setEmail(dados.email());
        responsavel.setEndereco(enderecoService.preencherEnderco(dados.endereco()));
        responsavel.setRg(dados.rg());

        repository.save(responsavel);

        return responsavel;
    }

    public Page<DadosListagemResponsavel> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemResponsavel::new);
    }

    public Responsavel atualizar(@Valid DadosAtualizacaoResponsavel dados) {
        var responsavel = repository.findById(dados.cpf()).get();

        if (dados.nome() != null) {
            responsavel.setNome(dados.nome());
        }
        if (dados.email() != null) {
            responsavel.setEmail(dados.email());
        }
        if (dados.endereco() != null) {
            var endereco = enderecoService.atualizarEndereco(responsavel.getEndereco(), dados.endereco());
            responsavel.setEndereco(endereco);
        }

        repository.save(responsavel);

        return responsavel;
    }

    public void excluir(String cpf) {
        var responsavel = repository.findById(cpf).get();
        repository.delete(responsavel);
    }

    public Responsavel detalhar(String cpf) {
        return repository.findById(cpf).get();
    }

}
