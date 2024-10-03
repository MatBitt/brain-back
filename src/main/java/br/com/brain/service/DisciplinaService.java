package br.com.brain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.brain.domain.disciplina.DadosAtualizacaoDisciplina;
import br.com.brain.domain.disciplina.DadosCadastroDisciplina;
import br.com.brain.domain.disciplina.DadosListagemDisciplina;
import br.com.brain.domain.disciplina.Disciplina;
import br.com.brain.domain.disciplina.DisciplinaRepository;
import br.com.brain.domain.grupo.GrupoDisciplina;
import br.com.brain.domain.serie.Serie;
import br.com.brain.domain.unidade.Unidade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository repository;

    @PersistenceContext
    private EntityManager em;

    public void save(Disciplina disciplina) {
        repository.save(disciplina);
    }

    public Disciplina cadastrarDisciplina(@Valid DadosCadastroDisciplina dados) {

        GrupoDisciplina grupo = em.getReference(GrupoDisciplina.class, dados.grupoId());
        Serie serie = em.getReference(Serie.class, dados.serieId());
        Unidade unidade = em.getReference(Unidade.class, dados.unidadeId());
        var disciplina = new Disciplina();
        disciplina.setNome(dados.nome());
        disciplina.setCargaHoraria(dados.cargaHoraria());
        disciplina.setGrupo(grupo);
        disciplina.setSerie(serie);
        disciplina.setUnidade(unidade);

        repository.save(disciplina);

        return disciplina;
    }

    public Page<DadosListagemDisciplina> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemDisciplina::new);
    }

    public Disciplina atualizar(@Valid DadosAtualizacaoDisciplina dados) {
        var disciplina = repository.findById(dados.id()).get();

        if (dados.nome() != null) {
            disciplina.setNome(dados.nome());
        }
        if (dados.unidadeId() != null) {
            Unidade unidade = em.getReference(Unidade.class, dados.unidadeId());
            disciplina.setUnidade(unidade);
        }
        if (dados.serieId() != null) {
            Serie serie = em.getReference(Serie.class, dados.serieId());
            disciplina.setSerie(serie);
        }
        if (dados.cargaHoraria() != null) {
            disciplina.setCargaHoraria(dados.cargaHoraria());
        }
        if (dados.grupoId() != null) {
            GrupoDisciplina grupo = em.getReference(GrupoDisciplina.class, dados.grupoId());
            disciplina.setGrupo(grupo);
        }

        repository.save(disciplina);

        return disciplina;
    }

    public void excluir(Long id) {
        var disciplina = repository.findById(id).get();
        repository.delete(disciplina);
    }

    public Disciplina detalhar(Long id) {
        return repository.findById(id).get();
    }

}
