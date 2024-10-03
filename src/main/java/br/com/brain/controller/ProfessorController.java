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

import br.com.brain.domain.professor.*;
import br.com.brain.service.ProfessorService;

@RestController
@RequestMapping("api/professor")
@RequiredArgsConstructor
//@SecurityRequirement(name = "bearer-key")
public class ProfessorController {

    private final ProfessorService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoProfessor> cadastrar(@RequestBody @Valid DadosCadastroProfessor dados, UriComponentsBuilder uriBuilder) {
        var professor = service.cadastrarProfessor(dados);
        var uri = uriBuilder.path("/professor/{matricula}").buildAndExpand(professor.getCpf()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoProfessor(professor));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemProfessor>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoProfessor> atualizar(@RequestBody @Valid DadosAtualizacaoProfessor dados) {
        var professor = service.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoProfessor(professor));
    }

    @DeleteMapping("/{matricula}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoProfessor> excluir(@PathVariable String matricula) {
        service.excluir(matricula);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<DadosDetalhamentoProfessor> detalhar(@PathVariable String matricula) {
        var professor = service.detalhar(matricula);
        return ResponseEntity.ok(new DadosDetalhamentoProfessor(professor));
    }

}
