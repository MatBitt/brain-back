package br.com.brain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.brain.domain.unidade.DadosAtualizacaoUnidade;
import br.com.brain.domain.unidade.DadosCadastroUnidade;
import br.com.brain.domain.unidade.DadosListagemUnidade;
import br.com.brain.domain.unidade.Unidade;
import br.com.brain.domain.unidade.UnidadeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnidadeService {

    private final UnidadeRepository repository;

    @PersistenceContext
    private EntityManager em;

    public void save(Unidade unidade) {
        repository.save(unidade);
    }

    public Unidade cadastrarUnidade(@Valid DadosCadastroUnidade dados) {

        var unidade = new Unidade();
        unidade.setNome(dados.nome());
        
        repository.save(unidade);

        return unidade;
    }

    public Page<DadosListagemUnidade> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemUnidade::new);
    }

    public Unidade atualizar(@Valid DadosAtualizacaoUnidade dados) {
        var unidade = repository.findById(dados.id()).get();

        if (dados.nome() != null) {
            unidade.setNome(dados.nome());
        }

        repository.save(unidade);

        return unidade;
    }

    public void excluir(Long id) {
        var unidade = repository.findById(id).get();
        repository.delete(unidade);
    }

    public Unidade detalhar(Long id) {
        return repository.findById(id).get();
    }

}
