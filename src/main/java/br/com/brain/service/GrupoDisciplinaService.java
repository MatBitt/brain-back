package br.com.brain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.brain.domain.grupo.DadosAtualizacaoGrupoDisciplina;
import br.com.brain.domain.grupo.DadosCadastroGrupoDisciplina;
import br.com.brain.domain.grupo.DadosListagemGrupoDisciplina;
import br.com.brain.domain.grupo.GrupoDisciplina;
import br.com.brain.domain.grupo.GrupoDisciplinaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GrupoDisciplinaService {

    private final GrupoDisciplinaRepository repository;

    @PersistenceContext
    private EntityManager em;

    public void save(GrupoDisciplina grupo) {
        repository.save(grupo);
    }

    public GrupoDisciplina cadastrarGrupoDisciplina(@Valid DadosCadastroGrupoDisciplina dados) {

        var grupo = new GrupoDisciplina();
        grupo.setNome(dados.nome());
        grupo.setArea(dados.area());
        
        repository.save(grupo);

        return grupo;
    }

    public Page<DadosListagemGrupoDisciplina> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemGrupoDisciplina::new);
    }

    public GrupoDisciplina atualizar(@Valid DadosAtualizacaoGrupoDisciplina dados) {
        var grupo = repository.findById(dados.id()).get();

        if (dados.nome() != null) {
            grupo.setNome(dados.nome());
        }
        if (dados.area() != null) {
            grupo.setArea(dados.area());
        }

        repository.save(grupo);

        return grupo;
    }

    public void excluir(Long id) {
        var grupo = repository.findById(id).get();
        repository.delete(grupo);
    }

    public GrupoDisciplina detalhar(Long id) {
        return repository.findById(id).get();
    }

}
