package br.com.frentecorretora.fakeatm.models;

import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "Pacote")
public class PacoteModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    /* 
    @Column(name = "tipo_de_nota")
    private int tipoDeNota;

    @Column(name = "limite_de_notas", nullable = true, columnDefinition = "INT DEFAULT 0") 
    private int limiteDeNotas;   

    @Column(name = "criadoEm")
    private Date criadoEm;

    @Column(name = "abertoEm")
    private Date abertoEm;
    */

    @ManyToOne
    @JoinColumn(name = "conta_id")
    @JsonIgnore
    //@JsonIgnoreProperties({"conta","cliente","pacote","pacotes"})
    private ContaModel conta;

    /*
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transacao_id", referencedColumnName = "id", unique = true)
    private TransacaoModel transacao;
    */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    /* 
    public int getTipoDeNota() {
        return tipoDeNota;
    }

    public void setTipoDeNota(int tipoDeNota) {
        this.tipoDeNota = tipoDeNota;
    }

    public int getLimiteDeNotas() {
        return limiteDeNotas;
    }

    public void setLimiteDeNotas(int limiteDeNotas) {
        this.limiteDeNotas = limiteDeNotas;
    }

    public Date getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Date criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Date getAbertoEm() {
        return abertoEm;
    }

    public void setAbertoEm(Date abertoEm) {
        this.abertoEm = abertoEm;
    }
    */
    public ContaModel getConta() {
        return conta;
    }

    public void setConta(ContaModel conta) {
        this.conta = conta;
    }
    /*
    public TransacaoModel getTransacao() {
        return transacao;
    }

    public void setTransacao(TransacaoModel transacao) {
        this.transacao = transacao;
    }
    */

}
