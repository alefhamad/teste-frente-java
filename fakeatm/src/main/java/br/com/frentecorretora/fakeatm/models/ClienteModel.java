package br.com.frentecorretora.fakeatm.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clientes")
public class ClienteModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name = "cliente_nome")
    private String clienteNome;

    @Column(name = "cliente_senha")
    private String clienteSenha;

    @Column(name = "cliente_endereco")
    private String clienteEndereco;

    @Column(name = "cliente_cpf")
    private String clienteCpf;

    @OneToOne(mappedBy = "cliente")
    @JsonIgnoreProperties("cliente")
    private ContaModel conta;
    //deafult constructor for
    public ClienteModel(){
    }

    public ClienteModel(String clienteNome, String clienteSenha, String clienteEndereco, String clienteCpf) {
        this.clienteNome = clienteNome;
        this.clienteSenha = clienteSenha;
        this.clienteEndereco = clienteEndereco;
        this.clienteCpf = clienteCpf;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getClienteSenha() {
        return clienteSenha;
    }

    public void setClienteSenha(String clienteSenha) {
        this.clienteSenha = clienteSenha;
    }

    public String getClienteEndereco() {
        return clienteEndereco;
    }

    public void setClienteEndereco(String clienteEndereco) {
        this.clienteEndereco = clienteEndereco;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public ContaModel getConta() {
        return conta;
    }

    public void setConta(ContaModel conta) {
        this.conta = conta;
    }
    
}
