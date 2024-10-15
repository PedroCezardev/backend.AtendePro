package com.backend.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "OrdemChegada") 
@Data 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrdemChegada {

    @Id
    private String id;

    private List<Long> senhas;

    private String diaAtendimento;

    private LocalDateTime horaInicio;

    private LocalDateTime horaTermino;

    private List<User> clientes;
    
}
