package com.paguepouco.api.infra.services;


import com.paguepouco.api.dtos.Consulta.DadosAgendamentoConsulta;
import com.paguepouco.api.dtos.Consulta.DadosDetalhamentoConsulta;
import com.paguepouco.api.infra.exception.ValidacaoConsulta;
import com.paguepouco.api.model.Consulta;
import com.paguepouco.api.model.Farmaceutico;
import com.paguepouco.api.repositories.ClienteRepository;
import com.paguepouco.api.repositories.ConsultaRepository;
import com.paguepouco.api.repositories.FarmaceuticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaConsultas {

        @Autowired
        private ConsultaRepository consultaRepository;

        @Autowired
        private FarmaceuticoRepository farmaceuticoRepository;

        @Autowired
        private ClienteRepository clienteRepository;

        /* esse parametro em dados tras apenas o id do médico e do paciente, porem precisamos da
           entidade farmaceutico/cliente inteiros para fazer o relacionamento na tabela, observe na linha 50.  */
        public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {

                // porem antes de fazemos a relação vamos verificar algumas coisas.


                // precisamos verificar primeiro se o id do cliente está a vir.
                if (!clienteRepository.existsById(dados.id_cliente())) {
                        throw new ValidacaoConsulta("Id do Cliente informado não existe no banco de dados.");
                }

                // como a escolha do farmaceutico é opcional, primeiro se verifica se ele veio e dps se ele existe, se ambas forem verdade levarar a um erro.
                if (dados.id_farmaceutico() != null && !farmaceuticoRepository.existsById(dados.id_farmaceutico())) {
                        throw new ValidacaoConsulta("Id do Farmaceutico informado não existe no banco de dados.");

                }

                // agora que verificamos tudo vamos fazer os relacionamentos

                //
                var cliente = clienteRepository.getReferenceById(dados.id_cliente());

                // a escolha de farmaceutico tem regras propias por isso é colocado em um metodo
                var farmaceutico = escolherFarmaceutico(dados);


                // se caso não houver medico dispónivel nessa especialidade
                if (farmaceutico == null){
                        throw new ValidacaoConsulta("Não existe Medico disponivel nessa especialidade");
                }

                // criando a entidade consulta, passando os parametros de medico, paciente e data.
                var consulta = new Consulta(null, dados.tipoConsulta(), farmaceutico, cliente, dados.data());

                // salvando no banco.
                consultaRepository.save(consulta);

                return new DadosDetalhamentoConsulta(consulta);

        }


           // esse metodo define as regras da escolha do farmaceutico
        private Farmaceutico escolherFarmaceutico(DadosAgendamentoConsulta dados) {

                // nesse primeiro caso se verifica se o id do farmaceutico foi passado na requisição, se for o caso o metodo acaba aqui com o obejto medico sendo retornado.
                if (dados.id_farmaceutico() != null ){
                 return farmaceuticoRepository.getReferenceById(dados.id_farmaceutico());
        }

                 // se caso o id do farmaceutico não for passado e nem a especialidade não é possivel marcar consulta, então se retorna um erro.
                 if(dados.especialidade() == null){

                 throw new ValidacaoConsulta("Especialidade é obrigatoria quando o medico não foi escolhido");
        }

                   // se caso o id do farmaceutico não for passado porem a especialidade sim , chamasse o metodo que busca um farmaceutico aleatorio
                   return farmaceuticoRepository.escolherFarmaceuticoAleatorio(dados.especialidade(), dados.data());

        }


}
