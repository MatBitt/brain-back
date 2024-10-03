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

import br.com.brain.domain.serie.DadosAtualizacaoSerie;
import br.com.brain.domain.serie.DadosCadastroSerie;
import br.com.brain.domain.serie.DadosDetalhamentoSerie;
import br.com.brain.domain.serie.DadosListagemSerie;
import br.com.brain.domain.serie.Serie;
import br.com.brain.domain.serie.SerieRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/serie")
//@SecurityRequirement(name = "bearer-key")
public class SerieController {

    private final SerieRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoSerie> cadastrar(@RequestBody @Valid DadosCadastroSerie dados, UriComponentsBuilder uriBuilder) {
        var serie = new Serie(dados);
        repository.save(serie);

        var uri = uriBuilder.path("/serie/{id}").buildAndExpand(serie.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoSerie(serie));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemSerie>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemSerie::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoSerie> atualizar(@RequestBody @Valid DadosAtualizacaoSerie dados) {
        var serie = repository.findById(dados.id()).get();
        serie.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoSerie(serie));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoSerie> excluir(@PathVariable Long id) {
        var serie = repository.findById(id).get();
        repository.delete(serie);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoSerie> detalhar(@PathVariable Long id) {
        var serie = repository.findById(id).get();
        return ResponseEntity.ok(new DadosDetalhamentoSerie(serie));
    }
}
