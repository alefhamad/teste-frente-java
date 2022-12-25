package br.com.frentecorretora.fakeatm.models;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pacotes")
public class PacoteModel {
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPacote;

    @Column(name = "data_criacao")
    private Instant dataCriacao;

    @Column(name = "data_abertura")
    private Instant dataAbertura;

    @Column(name = "data_fechamento")
    private Instant dataFechamento;

    
    @ManyToOne
    @JsonIgnoreProperties({"pacotes","cliente","contaNumero","conta","contas"})
    @JoinColumn(name = "conta_id")
    private ContaModel conta;

    @OneToOne(mappedBy = "pacote", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"pacotes","cliente","pacote"})
    private TransacaoModel transacao;

    public PacoteModel(){

    }

    public PacoteModel(Instant dataCriacao, Instant dataAbertura, Instant dataFechamento, ContaModel conta) {
        this.dataCriacao = dataCriacao;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.conta = conta;
    }

    public long getIdPacote() {
        return idPacote;
    }

    public void setIdPacote(long id) {
        this.idPacote = id;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Instant getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Instant dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Instant getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Instant dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public ContaModel getConta() {
        return conta;
    }

    public void setConta(ContaModel conta) {
        this.conta = conta;
    }

    public TransacaoModel getTransacao() {
        return transacao;
    }

    public void setTransacao(TransacaoModel transacao) {
        this.transacao = transacao;
    }

    

}
