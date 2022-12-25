package br.com.frentecorretora.fakeatm.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "transacoes")
public class TransacaoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //limite de 5000 reais por transação
    @Column(name = "valor")
    private double valor;

    @Column(name = "data_transacao")
    private Date dataTransacao;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "pacote_id")
    private PacoteModel pacote;

    public TransacaoModel() {
    }

    public TransacaoModel(double valor, Date dataTransacao, PacoteModel pacote) {
        this.valor = valor;
        this.dataTransacao = dataTransacao;
        this.pacote = pacote;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public PacoteModel getPacote() {
        return pacote;
    }

    public void setPacote(PacoteModel pacote) {
        this.pacote = pacote;
    }

    
}
