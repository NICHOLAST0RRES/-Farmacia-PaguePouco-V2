package com.paguepouco.api.dtos.Consulta;

import com.paguepouco.api.model.Consulta;
import com.paguepouco.api.model.TipoConsulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, Long id_farmaceutico, Long id_cliente, LocalDateTime data, TipoConsulta tipoConsulta) {

    public DadosDetalhamentoConsulta(Consulta consulta) {

        this(consulta.getId(),consulta.getFarmaceutico().getId(), consulta.getCliente().getId(), consulta.getData(), consulta.getTipoConsulta());


    }

}
