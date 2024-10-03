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
import br.com.brain.service.AlunoService;

@RestController
@RequestMapping("api/aluno")
@RequiredArgsConstructor
//@SecurityRequirement(name = "bearer-key")
public class AlunoController {

    private final AlunoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAluno> cadastrar(@RequestBody @Valid DadosCadastroAluno dados, UriComponentsBuilder uriBuilder) {
        var aluno = service.cadastrarAluno(dados);
        var uri = uriBuilder.path("/aluno/{matricula}").buildAndExpand(aluno.getMatricula()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoAluno(aluno));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAluno>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAluno> atualizar(@RequestBody @Valid DadosAtualizacaoAluno dados) {
        var aluno = service.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

    @DeleteMapping("/{matricula}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoAluno> excluir(@PathVariable String matricula) {
        service.excluir(matricula);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<DadosDetalhamentoAluno> detalhar(@PathVariable String matricula) {
        var aluno = service.detalhar(matricula);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

}
