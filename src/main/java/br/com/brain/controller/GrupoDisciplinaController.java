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

import br.com.brain.domain.grupo.DadosAtualizacaoGrupoDisciplina;
import br.com.brain.domain.grupo.DadosCadastroGrupoDisciplina;
import br.com.brain.domain.grupo.DadosDetalhamentoGrupoDisciplina;
import br.com.brain.domain.grupo.DadosListagemGrupoDisciplina;
import br.com.brain.service.GrupoDisciplinaService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/grupo-disciplina")
//@SecurityRequirement(name = "bearer-key")
public class GrupoDisciplinaController {

    private final GrupoDisciplinaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoGrupoDisciplina> cadastrar(@RequestBody @Valid DadosCadastroGrupoDisciplina dados, UriComponentsBuilder uriBuilder) {
        var grupoDisciplina = service.cadastrarGrupoDisciplina(dados);
        var uri = uriBuilder.path("/grupo-disciplina/{id}").buildAndExpand(grupoDisciplina.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoGrupoDisciplina(grupoDisciplina));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemGrupoDisciplina>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoGrupoDisciplina> atualizar(@RequestBody @Valid DadosAtualizacaoGrupoDisciplina dados) {
        var grupoDisciplina = service.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoGrupoDisciplina(grupoDisciplina));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoGrupoDisciplina> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoGrupoDisciplina> detalhar(@PathVariable Long id) {
        var grupoDisciplina = service.detalhar(id);
        return ResponseEntity.ok(new DadosDetalhamentoGrupoDisciplina(grupoDisciplina));
    }
}
