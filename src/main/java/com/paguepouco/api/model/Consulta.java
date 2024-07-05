package com.paguepouco.api.model;




import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;


    @Enumerated(EnumType.STRING)
    private TipoConsulta tipoConsulta ;

    @JoinColumn(name = "farmaceutico_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Farmaceutico farmaceutico ;

    @JoinColumn(name = "cliente_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente ;

    private LocalDateTime data ;





}

