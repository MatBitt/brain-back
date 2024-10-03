package br.com.brain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.brain.domain.serie.DadosAtualizacaoSerie;
import br.com.brain.domain.serie.DadosCadastroSerie;
import br.com.brain.domain.serie.DadosListagemSerie;
import br.com.brain.domain.serie.Serie;
import br.com.brain.domain.serie.SerieRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SerieService {

    private final SerieRepository repository;

    @PersistenceContext
    private EntityManager em;

    public void save(Serie serie) {
        repository.save(serie);
    }

    public Serie cadastrarSerie(@Valid DadosCadastroSerie dados) {

        var serie = new Serie();
        serie.setNome(dados.nome());
        
        repository.save(serie);

        return serie;
    }

    public Page<DadosListagemSerie> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemSerie::new);
    }

    public Serie atualizar(@Valid DadosAtualizacaoSerie dados) {
        var serie = repository.findById(dados.id()).get();

        if (dados.nome() != null) {
            serie.setNome(dados.nome());
        }

        repository.save(serie);

        return serie;
    }

    public void excluir(Long id) {
        var serie = repository.findById(id).get();
        repository.delete(serie);
    }

    public Serie detalhar(Long id) {
        return repository.findById(id).get();
    }

}
