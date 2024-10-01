package br.com.brain.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.brain.domain.aluno.*;

@RestController
@RequestMapping("api/aluno")
@RequiredArgsConstructor
//@SecurityRequirement(name = "bearer-key")
public class AlunoController {

    private final AlunoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAluno> cadastrar(@RequestBody @Valid DadosCadastroAluno dados, UriComponentsBuilder uriBuilder) {
        var aluno = new Aluno(dados);
        repository.save(aluno);

        var uri = uriBuilder.path("/aluno/{cpf}").buildAndExpand(aluno.getCpf()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoAluno(aluno));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAluno>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemAluno::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAluno> atualizar(@RequestBody @Valid DadosAtualizacaoAluno dados) {
        var aluno = repository.getReferenceById(dados.cpf());
        aluno.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

    @DeleteMapping("/{cpf}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoAluno> excluir(@PathVariable String cpf) {
        var aluno = repository.getReferenceById(cpf);
        repository.delete(aluno);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<DadosDetalhamentoAluno> detalhar(@PathVariable String cpf) {
        var aluno = repository.getReferenceById(cpf);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

}
