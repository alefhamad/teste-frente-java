package br.com.frentecorretora.fakeatm.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "conta")
public class ContaModel {
    
    @Id
    @GeneratedValue
}
