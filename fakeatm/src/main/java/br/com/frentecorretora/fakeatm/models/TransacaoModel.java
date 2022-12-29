package br.com.frentecorretora.fakeatm.models;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "transacoes")
public class TransacaoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTransacao;

    //limite de 5000 reais por transação
    @Column(name = "valor")
    private double valor;

    @Column(name = "data_transacao")
    private Instant dataTransacao;

    @Column(name = "tipo_de_nota")
    private int tipoDeNota;

    @Column(name = "limite_de_notas")
    private int limiteDeNotas;

    @OneToOne
    //@JsonIgnore
    @JsonIgnoreProperties({"transacao","pacote","cliente","contaNumero","conta","contas"})
    @JoinColumn(name = "pacote_id", unique = true)
    private PacoteModel pacote;

    public TransacaoModel(){
        
    }

    public TransacaoModel(double valor, int tipoDeNota, int limiteDeNotas, PacoteModel pacote) {
        this.valor = valor;
        this.tipoDeNota = tipoDeNota;
        this.limiteDeNotas = limiteDeNotas;
        this.pacote = pacote;
    }



    public long getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(long id) {
        this.idTransacao = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Instant getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Instant dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public PacoteModel getPacote() {
        return pacote;
    }

    public void setPacote(PacoteModel pacote) {
        this.pacote = pacote;
    }

    public int getTipoDeNota() {
        return tipoDeNota;
    }

    public void setTipoDeNota(int tipoDeNota) {
        if(tipoDeNota == 10 || tipoDeNota == 50 || tipoDeNota == 100){
        this.tipoDeNota = tipoDeNota;
        } else {
            throw new IllegalArgumentException("Tipo de nota inválido");
        }
    }

    public int getLimiteDeNotas() {
        limiteDeNotas = 50;
        return limiteDeNotas;
    }

    public void setLimiteDeNotas(int limiteDeNotas) {
        if(limiteDeNotas > getLimiteDeNotas()){
            throw new IllegalArgumentException("Limite de notas inválido");
        }
        this.limiteDeNotas = limiteDeNotas;
    }
    
}
