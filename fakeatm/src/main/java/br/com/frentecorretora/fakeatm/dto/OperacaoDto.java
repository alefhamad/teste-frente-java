package br.com.frentecorretora.fakeatm.dto;

import lombok.Data;

@Data
public class OperacaoDto {
    
    private String conta;
    private Double valor;
    private int nota;
}
