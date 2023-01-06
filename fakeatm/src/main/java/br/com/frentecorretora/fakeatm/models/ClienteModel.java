package br.com.frentecorretora.fakeatm.models;


import javax.persistence.CascadeType;
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
    public long idCliente;

    @Column(name = "cliente_nome", nullable = false)
    private String clienteNome;

    //@JsonIgnore
    @Column(name = "cliente_senha", nullable = false)
    private String clienteSenha;

    @Column(name = "cliente_endereco", nullable = false)
    private String clienteEndereco;

    @Column(name = "cliente_cpf", nullable = false, unique = true)
    private String clienteCpf;

    @Column(name = "cliente_role", nullable = false)
    private String clienteRole;

    
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("cliente")
    private ContaModel conta;
    //deafult constructor for
    public ClienteModel(){
    }

    
    
    public ClienteModel(String clienteNome, String clienteSenha, String clienteEndereco, String clienteCpf,
            String clienteRole) {
        this.clienteNome = clienteNome;
        this.clienteSenha = clienteSenha;
        this.clienteEndereco = clienteEndereco;
        this.clienteCpf = clienteCpf;
        this.clienteRole = clienteRole;
    }



    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
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

    public String getClienteRole() {
        return clienteRole;
    }

    public void setClienteRole(String clienteRole) {
        this.clienteRole = clienteRole;
    }

}
