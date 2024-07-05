package com.paguepouco.api.dtos.Cliente;

import jakarta.validation.constraints.NotNull;

public record AtualizarCliente(@NotNull(message = "ERRO, ID DO FARMACEUTICO NÃO INFORMADO ")  Long id ,
                               String nome ,
                               String telefone,
                               String email,
                               String convenio
) {
}
