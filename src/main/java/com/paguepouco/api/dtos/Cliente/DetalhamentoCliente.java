package com.paguepouco.api.dtos.Cliente;

import com.paguepouco.api.model.Cliente;


public record DetalhamentoCliente(Long id, String nome, String telefone , String email , String convenio, boolean ativo ) {

    public DetalhamentoCliente(Cliente cliente) {

        this(cliente.getId(), cliente.getNome(),cliente.getTelefone(), cliente.getEmail() , cliente.getConvenio(),cliente.isAtivo());
    }


}
