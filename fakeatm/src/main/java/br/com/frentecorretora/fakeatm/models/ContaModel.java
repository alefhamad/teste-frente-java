package br.com.frentecorretora.fakeatm.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "contas")
public class ContaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idConta;

    
    @Column(name = "conta_numero", nullable = false, length = 6)
    private String contaNumero;

    @Column(name = "saldo_conta", nullable = true)
    private int saldoConta;

    @OneToOne
    //@JsonIgnoreProperties("conta")
    @JoinColumn(name = "cliente_id")
    private ClienteModel cliente;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("conta")
    private List <PacoteModel> pacotes;

    public ContaModel() {
    }

    public ContaModel(String contaNumero, ClienteModel cliente) {
        this.contaNumero = contaNumero;
        this.cliente = cliente;
    }
    
    public long getIdConta() {
        return idConta;
    }

    public void setIdConta(long idConta) {
        this.idConta = idConta;
    }

    public String getContaNumero() {
        return contaNumero;
    }

    public void setContaNumero(String contaNumero) {
        this.contaNumero = contaNumero;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public List<PacoteModel> getPacotes() {
        return pacotes;
    }

    public void setPacotes(List<PacoteModel> pacotes) {
        this.pacotes = pacotes;
    }

    public int getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(int saldoConta) {
        this.saldoConta = saldoConta;
    }

    

}
