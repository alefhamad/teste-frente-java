package br.com.frentecorretora.fakeatm.models;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @Column(name = "data_transacao", updatable = false)
    private Instant dataTransacao;

    @Column(name = "tipo_de_nota", nullable = true)
    private int tipoDeNota;

    @Column(name = "limite_de_notas", nullable = true)
    private int limiteDeNotas;

    @Column(name = "limite_de_valor", nullable = true)
    private double limiteDeValor;

    @Column(name = "status_transacao", nullable = true)
    private String statusTransacao;

    @Column(name = "quantidade_notas_utilizadas", nullable = true)
    private int quantidadeNotasUtilizadas;

    @ManyToOne
    @JsonIgnoreProperties({"transacao","pacote","cliente","contaNumero","conta","contas"})
    @JoinColumn(name = "pacote_id")
    private PacoteModel pacote;

    public TransacaoModel(){
        
    }

    public TransacaoModel(PacoteModel pacote) {
        this.pacote = pacote;
    }

    public TransacaoModel(double valor, int tipoDeNota, String statusTransacao, int quantidadeNotasUtilizadas, double limiteDeValor, PacoteModel pacote) {
        this.valor = valor;
        this.tipoDeNota = tipoDeNota;
        this.limiteDeNotas = 50;
        this.limiteDeValor = 5000.00;
        this.dataTransacao = Instant.now();
        this.statusTransacao = statusTransacao;
        this.quantidadeNotasUtilizadas = quantidadeNotasUtilizadas;
        this.limiteDeValor = limiteDeValor;
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

    public void setDataTransacao() {
        this.dataTransacao = Instant.now();
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
        this.limiteDeNotas = limiteDeNotas;
    }

    public double getLimiteDeValor() {
        return limiteDeValor;
    }

    public void setLimiteDeValor(double limiteDeValor) {
        this.limiteDeValor = limiteDeValor;
    }

    public String getStatusTransacao() {
        return statusTransacao;
    }

    public void setStatusTransacao(String statusTransacao) {
        this.statusTransacao = statusTransacao;
    }

    public int getQuantidadeNotasUtilizadas() {
        return quantidadeNotasUtilizadas;
    }

    public void setQuantidadeNotasUtilizadas(int quantidadeNotasUtilizadas) {
        this.quantidadeNotasUtilizadas = quantidadeNotasUtilizadas;
    } 
    
    
}
