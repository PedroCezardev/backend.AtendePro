package com.backend.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrdemChegadaDTO {
    
    private List<Long> senhas;

    private String diaAtendimento;

    private LocalDateTime horaInicio;

    private LocalDateTime horaTermino;

}
