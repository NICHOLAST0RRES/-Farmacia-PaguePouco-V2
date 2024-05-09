package com.paguepouco.api.dtos;

import com.paguepouco.api.model.Especialidade;
import com.paguepouco.api.model.Farmaceutico;

public record DetalhamentoFarmaceutico(Long id, String nome, String tipo , String crf , Especialidade especialidade, String turno ) {


    public DetalhamentoFarmaceutico(Farmaceutico farmaceutico) {

        this(farmaceutico.getId(), farmaceutico.getNome(),farmaceutico.getTipo(), farmaceutico.getCrf() , farmaceutico.getEspecialidade(),farmaceutico.getTurno());
    }

}