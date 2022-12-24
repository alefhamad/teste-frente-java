/* 
package br.com.frentecorretora.fakeatm.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "transacoes")
public class TransacaoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "valor_empacotado")
    private int valorEmpacotado;

    @OneToOne(mappedBy = "transacao")
    @JsonIgnore
    private PacoteModel pacote;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getValorEmpacotado() {
        return valorEmpacotado;
    }

    public void setValorEmpacotado(int valorEmpacotado) {
        this.valorEmpacotado = valorEmpacotado;
    }

    public PacoteModel getPacote() {
        return pacote;
    }

    public void setPacote(PacoteModel pacote) {
        this.pacote = pacote;
    }

    

}
 */