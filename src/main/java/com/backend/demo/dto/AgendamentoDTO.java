package com.backend.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AgendamentoDTO {

    private String servicos;

    private String promocao;

    private String profissional;

    private String diaAgendamento;

    private String horarioAgendamento;
    
}
