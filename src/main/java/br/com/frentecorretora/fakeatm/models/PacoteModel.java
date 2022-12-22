package br.com.frentecorretora.fakeatm.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Pacote")
public class PacoteModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "quantidate_de_notas", nullable = true, columnDefinition = "INT DEFAULT 0") 
    private int quantidateDeNotas;
}
