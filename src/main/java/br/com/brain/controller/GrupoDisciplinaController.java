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
import br.com.brain.domain.grupo.GrupoDisciplina;
import br.com.brain.domain.grupo.GrupoDisciplinaRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/grupo-disciplina")
//@SecurityRequirement(name = "bearer-key")
public class GrupoDisciplinaController {

    private final GrupoDisciplinaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoGrupoDisciplina> cadastrar(@RequestBody @Valid DadosCadastroGrupoDisciplina dados, UriComponentsBuilder uriBuilder) {
        var grupoDisciplina = new GrupoDisciplina(dados);
        repository.save(grupoDisciplina);

        var uri = uriBuilder.path("/grupo-disciplina/{id}").buildAndExpand(grupoDisciplina.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoGrupoDisciplina(grupoDisciplina));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemGrupoDisciplina>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemGrupoDisciplina::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoGrupoDisciplina> atualizar(@RequestBody @Valid DadosAtualizacaoGrupoDisciplina dados) {
        var grupoDisciplina = repository.findById(dados.id()).get();
        grupoDisciplina.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoGrupoDisciplina(grupoDisciplina));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoGrupoDisciplina> excluir(@PathVariable Long id) {
        var grupoDisciplina = repository.findById(id).get();
        repository.delete(grupoDisciplina);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoGrupoDisciplina> detalhar(@PathVariable Long id) {
        var grupoDisciplina = repository.findById(id).get();
        return ResponseEntity.ok(new DadosDetalhamentoGrupoDisciplina(grupoDisciplina));
    }
}
