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

import br.com.brain.domain.disciplina.DadosAtualizacaoDisciplina;
import br.com.brain.domain.disciplina.DadosCadastroDisciplina;
import br.com.brain.domain.disciplina.DadosDetalhamentoDisciplina;
import br.com.brain.domain.disciplina.DadosListagemDisciplina;
import br.com.brain.service.DisciplinaService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/disciplina")
//@SecurityRequirement(name = "bearer-key")
public class DisciplinaController {

    private final DisciplinaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoDisciplina> cadastrar(@RequestBody @Valid DadosCadastroDisciplina dados, UriComponentsBuilder uriBuilder) {
        var disciplina = service.cadastrarDisciplina(dados);
        var uri = uriBuilder.path("/disciplina/{id}").buildAndExpand(disciplina.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoDisciplina(disciplina));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemDisciplina>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoDisciplina> atualizar(@RequestBody @Valid DadosAtualizacaoDisciplina dados) {
        var disciplina = service.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoDisciplina(disciplina));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoDisciplina> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoDisciplina> detalhar(@PathVariable Long id) {
        var disciplina = service.detalhar(id);
        return ResponseEntity.ok(new DadosDetalhamentoDisciplina(disciplina));
    }
}
