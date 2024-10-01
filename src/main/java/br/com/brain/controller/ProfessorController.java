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

@RestController
@RequestMapping("api/professor")
@RequiredArgsConstructor
//@SecurityRequirement(name = "bearer-key")
public class ProfessorController {

    private final ProfessorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoProfessor> cadastrar(@RequestBody @Valid DadosCadastroProfessor dados, UriComponentsBuilder uriBuilder) {
        var professor = new Professor(dados);
        repository.save(professor);

        var uri = uriBuilder.path("/professor/{cpf}").buildAndExpand(professor.getCpf()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoProfessor(professor));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemProfessor>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemProfessor::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoProfessor> atualizar(@RequestBody @Valid DadosAtualizacaoProfessor dados) {
        var professor = repository.getReferenceById(dados.cpf());
        professor.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoProfessor(professor));
    }

    @DeleteMapping("/{cpf}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoProfessor> excluir(@PathVariable String cpf) {
        var professor = repository.getReferenceById(cpf);
        repository.delete(professor);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<DadosDetalhamentoProfessor> detalhar(@PathVariable String cpf) {
        var professor = repository.getReferenceById(cpf);
        return ResponseEntity.ok(new DadosDetalhamentoProfessor(professor));
    }

}
