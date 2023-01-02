package br.com.frentecorretora.fakeatm.models;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

    @Column(name = "status_pacote")
    private String statusPacote;

    
    @ManyToOne
    @JsonIgnoreProperties({"pacotes","cliente","contaNumero","conta","contas","saldoConta","id"})
    @JoinColumn(name = "conta_id")
    private ContaModel conta;

    @OneToMany(mappedBy = "pacote", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"pacotes","cliente","pacote"})
    private List<TransacaoModel> transacao;

    public PacoteModel(){

    }

    public PacoteModel(ContaModel conta){
        this.conta = conta;
        this.dataCriacao = Instant.now();
        this.statusPacote = "Aberto";
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

    public void setDataCriacao() {
        this.dataCriacao = Instant.now();
    }

    public Instant getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura() {
        this.dataAbertura = Instant.now();
    }

    public Instant getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento() {
        this.dataFechamento = Instant.now();
    }

    public ContaModel getConta() {
        return conta;
    }

    public void setConta(ContaModel conta) {
        this.conta = conta;
    }

    public String getStatusPacote() {
        return statusPacote;
    }

    public void setStatusPacote(String statusPacote) {
        this.statusPacote = statusPacote;
    }

    public List<TransacaoModel> getTransacao() {
        return transacao;
    }

    public void setTransacao(List<TransacaoModel> transacao) {
        this.transacao = transacao;
    }
    
}
