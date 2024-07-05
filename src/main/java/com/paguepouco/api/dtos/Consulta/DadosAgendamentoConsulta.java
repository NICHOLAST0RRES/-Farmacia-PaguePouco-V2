package com.paguepouco.api.dtos.Consulta;

import com.paguepouco.api.model.Especialidade;
import com.paguepouco.api.model.TipoConsulta;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(Long id_farmaceutico,  @NotNull Long id_cliente,  @NotNull@Future LocalDateTime data , TipoConsulta tipoConsulta , Especialidade especialidade) {
}
