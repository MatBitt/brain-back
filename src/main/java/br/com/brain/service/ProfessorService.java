package br.com.brain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.brain.domain.professor.DadosAtualizacaoProfessor;
import br.com.brain.domain.professor.DadosCadastroProfessor;
import br.com.brain.domain.professor.DadosListagemProfessor;
import br.com.brain.domain.professor.Professor;
import br.com.brain.domain.professor.ProfessorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository repository;
    private final EnderecoService enderecoService;

    @PersistenceContext
    private EntityManager em;

    public void save(Professor professor) {
        repository.save(professor);
    }

    public Professor cadastrarProfessor(@Valid DadosCadastroProfessor dados) {

        var professor = new Professor();
        professor.setCpf(dados.cpf());
        professor.setNome(dados.nome());
        professor.setNomeSocial(dados.nomeSocial());
        professor.setEmail(dados.email());
        professor.setEmailProfissional(dados.cpf() + "@gmail.com");
        professor.setDataDeNascimento(dados.dataDeNascimento());
        professor.setEndereco(enderecoService.preencherEnderco(dados.endereco()));
        professor.setGenero(dados.genero());
        professor.setCorRaca(dados.corRaca());
        professor.setCidadeNaturalidade(dados.cidadeNaturalidade());
        professor.setRg(dados.rg());
        professor.setMatricula("M" + dados.cpf());
        professor.setCarteiraDeTrabalho(dados.carteiraDeTrabalho());

        repository.save(professor);

        return professor;
    }

    public Page<DadosListagemProfessor> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemProfessor::new);
    }

    public Professor atualizar(@Valid DadosAtualizacaoProfessor dados) {
        var professor = repository.findByMatricula(dados.matricula()).orElseThrow(
                () -> new EntityNotFoundException("Professor de matricula " + dados.matricula() + " não existe."));
        ;

        if (dados.nome() != null) {
            professor.setNome(dados.nome());
        }
        if (dados.dataDeNascimento() != null) {
            professor.setDataDeNascimento(dados.dataDeNascimento());
        }
        if (dados.email() != null) {
            professor.setEmail(dados.email());
        }
        if (dados.endereco() != null) {
            var endereco = enderecoService.atualizarEndereco(professor.getEndereco(), dados.endereco());
            professor.setEndereco(endereco);
        }

        repository.save(professor);

        return professor;
    }

    public void excluir(String matricula) {
        var professor = repository.findByMatricula(matricula).orElseThrow(
                () -> new EntityNotFoundException("Professor de matricula " + matricula + " não existe."));
        repository.delete(professor);
    }

    public Professor detalhar(String matricula) {
        return repository.findByMatricula(matricula).orElseThrow(
                () -> new EntityNotFoundException("Professor de matricula " + matricula + " não existe."));
    }

}
