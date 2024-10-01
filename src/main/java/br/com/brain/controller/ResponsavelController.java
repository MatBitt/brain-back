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

import br.com.brain.domain.responsavel.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/responsavel")
//@SecurityRequirement(name = "bearer-key")
public class ResponsavelController {

    private final ResponsavelRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoResponsavel> cadastrar(@RequestBody @Valid DadosCadastroResponsavel dados, UriComponentsBuilder uriBuilder) {
        var responsavel = new Responsavel(dados);
        repository.save(responsavel);

        var uri = uriBuilder.path("/responsavel/{cpf}").buildAndExpand(responsavel.getCpf()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoResponsavel(responsavel));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemResponsavel>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemResponsavel::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoResponsavel> atualizar(@RequestBody @Valid DadosAtualizacaoResponsavel dados) {
        var responsavel = repository.findByCpf(dados.cpf());
        responsavel.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoResponsavel(responsavel));
    }

    @DeleteMapping("/{cpf}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoResponsavel> excluir(@PathVariable String cpf) {
        var responsavel = repository.findByCpf(cpf);
        repository.delete(responsavel);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<DadosDetalhamentoResponsavel> detalhar(@PathVariable String cpf) {
        var responsavel = repository.findByCpf(cpf);
        return ResponseEntity.ok(new DadosDetalhamentoResponsavel(responsavel));
    }
}
