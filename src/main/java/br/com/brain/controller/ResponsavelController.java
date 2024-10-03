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
import br.com.brain.service.ResponsavelService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/responsavel")
//@SecurityRequirement(name = "bearer-key")
public class ResponsavelController {

    private final ResponsavelService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoResponsavel> cadastrar(@RequestBody @Valid DadosCadastroResponsavel dados, UriComponentsBuilder uriBuilder) {
        var responsavel = service.cadastrarResponsavel(dados);
        var uri = uriBuilder.path("/responsavel/{cpf}").buildAndExpand(responsavel.getCpf()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoResponsavel(responsavel));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemResponsavel>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoResponsavel> atualizar(@RequestBody @Valid DadosAtualizacaoResponsavel dados) {
        var responsavel = service.atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoResponsavel(responsavel));
    }

    @DeleteMapping("/{cpf}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoResponsavel> excluir(@PathVariable String cpf) {
        service.excluir(cpf);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<DadosDetalhamentoResponsavel> detalhar(@PathVariable String cpf) {
        var responsavel = service.detalhar(cpf);
        return ResponseEntity.ok(new DadosDetalhamentoResponsavel(responsavel));
    }
}
