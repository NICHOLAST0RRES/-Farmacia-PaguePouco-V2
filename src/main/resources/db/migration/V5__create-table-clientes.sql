create table clientes(

                              id bigint not null auto_increment,
                              nome varchar(100) not null,
                              telefone varchar(100) not null,
                              email varchar(100) not null,
                              convenio varchar(100) not null,
                              ativo tinyint not null,

                              primary key(id)

);
