create table consultas(

                          id bigint not null auto_increment,
                          farmaceutico_id bigint not null,
                          cliente_id bigint not null,
                          data datetime not null,
                          tipo_consulta varchar(100) not null,

                          primary key(id),
                          constraint fk_consultas_farmaceutico_id foreign key(farmaceutico_id) references farmaceuticos(id),
                          constraint fk_consultas_cliente_id foreign key(cliente_id) references clientes(id)

);