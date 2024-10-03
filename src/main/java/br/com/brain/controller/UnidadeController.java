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
import br.com.brain.domain.unidade.Unidade;
import br.com.brain.domain.unidade.UnidadeRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/unidade")
//@SecurityRequirement(name = "bearer-key")
public class UnidadeController {

    private final UnidadeRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUnidade> cadastrar(@RequestBody @Valid DadosCadastroUnidade dados, UriComponentsBuilder uriBuilder) {
        var unidade = new Unidade(dados);
        repository.save(unidade);

        var uri = uriBuilder.path("/unidade/{id}").buildAndExpand(unidade.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUnidade(unidade));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUnidade>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemUnidade::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUnidade> atualizar(@RequestBody @Valid DadosAtualizacaoUnidade dados) {
        var unidade = repository.findById(dados.id()).get();
        unidade.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoUnidade(unidade));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoUnidade> excluir(@PathVariable Long id) {
        var unidade = repository.findById(id).get();
        repository.delete(unidade);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUnidade> detalhar(@PathVariable Long id) {
        var unidade = repository.findById(id).get();
        return ResponseEntity.ok(new DadosDetalhamentoUnidade(unidade));
    }
}
