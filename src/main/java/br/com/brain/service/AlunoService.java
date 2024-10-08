package br.com.brain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.brain.domain.aluno.DadosAtualizacaoAluno;
import br.com.brain.domain.aluno.DadosCadastroAluno;
import br.com.brain.domain.aluno.DadosListagemAluno;
import br.com.brain.domain.aluno.Aluno;
import br.com.brain.domain.aluno.AlunoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository repository;
    private final EnderecoService enderecoService;

    @PersistenceContext
    private EntityManager em;

    public void save(Aluno aluno) {
        repository.save(aluno);
    }

    public Aluno cadastrarAluno(@Valid DadosCadastroAluno dados) {

        var aluno = new Aluno();
        aluno.setCpf(dados.cpf());
        aluno.setNome(dados.nome());
        aluno.setNomeSocial(dados.nomeSocial());
        aluno.setEmail(dados.email());
        aluno.setEmailEscolar(dados.cpf() + "@gmail.com");
        aluno.setDataDeNascimento(dados.dataDeNascimento());
        aluno.setEndereco(enderecoService.preencherEnderco(dados.endereco()));
        aluno.setGenero(dados.genero());
        aluno.setCorRaca(dados.corRaca());
        aluno.setCidadeNaturalidade(dados.cidadeNaturalidade());
        aluno.setRg(dados.rg());
        aluno.setMatricula("M" + dados.cpf());
        aluno.setTipoSanguineo(dados.tipoSanguineo());

        repository.save(aluno);

        return aluno;
    }

    public Page<DadosListagemAluno> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemAluno::new);
    }

    public Aluno atualizar(@Valid DadosAtualizacaoAluno dados) {
        var aluno = repository.findByMatricula(dados.matricula()).orElseThrow(
                () -> new EntityNotFoundException("Aluno de matricula " + dados.matricula() + " não existe."));

        if (dados.nome() != null) {
            aluno.setNome(dados.nome());
        }
        if (dados.dataDeNascimento() != null) {
            aluno.setDataDeNascimento(dados.dataDeNascimento());
        }
        if (dados.email() != null) {
            aluno.setEmail(dados.email());
        }
        if (dados.endereco() != null) {
            var endereco = enderecoService.atualizarEndereco(aluno.getEndereco(), dados.endereco());
            aluno.setEndereco(endereco);
        }

        repository.save(aluno);

        return aluno;
    }

    public void excluir(String matricula) {
        var aluno = repository.findByMatricula(matricula).orElseThrow(
                () -> new EntityNotFoundException("Aluno de matricula " + matricula + " não existe."));
        repository.delete(aluno);
    }

    public Aluno detalhar(String matricula) {
        var aluno = repository.findByMatricula(matricula).orElseThrow(
                () -> new EntityNotFoundException("Aluno de matricula " + matricula + " não existe."));
        return aluno;
    }

}
