package com.paguepouco.api.model;


import com.paguepouco.api.dtos.Cliente.AtualizarCliente;
import com.paguepouco.api.dtos.Cliente.CadastroCliente;
import com.paguepouco.api.dtos.Farmaceutico.AtualizarFarmaceutico;
import com.paguepouco.api.dtos.Farmaceutico.CadastroFarmaceutico;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String nome ;
    private String telefone ;
    private String email ;
    private String convenio ;
    private boolean ativo ;

    public Cliente (CadastroCliente dados){
        this.ativo = true ;
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.convenio = dados.convenio();

    }

   public void atualizarCliente(AtualizarCliente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }

        if(dados.convenio() != null) {
            this.convenio = dados.convenio();
        }

    }


    // em banco de dados é mais recomendavel mudar o status para inativo doq apagar completamente do BDs
    public void desativar() {
        this.ativo = false ;
    }

    public void reativar(){
        this.ativo = true ;
    }



}
