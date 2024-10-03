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

import br.com.brain.domain.unidade.DadosAtualizacaoUnidade;
import br.com.brain.domain.unidade.DadosCadastroUnidade;
import br.com.brain.domain.unidade.DadosDetalhamentoUnidade;
import br.com.brain.domain.unidade.DadosListagemUnidade;
import br.com.brain.service.UnidadeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/unidade")
//@SecurityRequirement(name = "bearer-key")
public class UnidadeController {

    private final UnidadeService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUnidade> cadastrar(@RequestBody @Valid DadosCadastroUnidade dados, UriComponentsBuilder uriBuilder) {
        var unidade = service.cadastrarUnidade(dados);
        var uri = uriBuilder.path("/unidade/{id}").buildAndExpand(unidade.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUnidade(unidade));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUnidade>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUnidade> atualizar(@RequestBody @Valid DadosAtualizacaoUnidade dados) {
        var unidade = service.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoUnidade(unidade));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoUnidade> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUnidade> detalhar(@PathVariable Long id) {
        var unidade = service.detalhar(id);
        return ResponseEntity.ok(new DadosDetalhamentoUnidade(unidade));
    }
}
