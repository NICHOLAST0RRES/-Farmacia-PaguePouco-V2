package com.paguepouco.api.repositories;

import com.paguepouco.api.model.Especialidade;
import com.paguepouco.api.model.Farmaceutico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;


public interface FarmaceuticoRepository extends JpaRepository<Farmaceutico, Long> {
    Page<Farmaceutico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
                select f from Farmaceutico f
                where
                f.ativo = true
                and
                f.especialidade = :especialidade
                and
                f.id not in(
                        select c.farmaceutico.id from Consulta c
                        where
                        c.data = :data
                )
                order by rand()
                limit 1
                """)
    Farmaceutico escolherFarmaceuticoAleatorio(Especialidade especialidade, LocalDateTime data);

}
