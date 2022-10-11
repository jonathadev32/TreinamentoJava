package com.indracompany.treinamento.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
public class ExtratoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDate data;
	
    private String tipoDeOperacao;
    
    private Double valor;
    
    private String agencia;
    
    private String numeroConta;
    
    private String observacao;
	
}
