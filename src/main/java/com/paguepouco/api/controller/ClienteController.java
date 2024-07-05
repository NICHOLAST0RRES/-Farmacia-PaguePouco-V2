package com.paguepouco.api.controller;


import com.paguepouco.api.dtos.Cliente.AtualizarCliente;
import com.paguepouco.api.dtos.Cliente.CadastroCliente;
import com.paguepouco.api.dtos.Cliente.DetalhamentoCliente;
import com.paguepouco.api.dtos.Medicamento.AtualizarMedicamento;
import com.paguepouco.api.dtos.Medicamento.DetalhamentoMedicamento;
import com.paguepouco.api.model.Cliente;
import com.paguepouco.api.repositories.ClienteRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("cliente")
@SecurityRequirement(name = "bearer-key")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@Valid @RequestBody CadastroCliente dados, UriComponentsBuilder capturarURI)    {

        var cliente = new Cliente(dados);
        repository.save(cliente); //
        var uri = capturarURI.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoCliente(cliente));

    }

    @GetMapping
    public ResponseEntity<Page<DetalhamentoCliente>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DetalhamentoCliente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar (@RequestBody @Valid AtualizarCliente dados){
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarCliente(dados);
        return ResponseEntity.ok(new DetalhamentoCliente(cliente));
    }


    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var cliente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhamentoCliente(cliente));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id)    {
        var cliente = repository.getReferenceById(id);
        cliente.desativar();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity reativar(@PathVariable Long id) {

        var cliente = repository.getReferenceById(id);
        cliente.reativar();

        return ResponseEntity.ok(new DetalhamentoCliente(cliente));
    }






}
