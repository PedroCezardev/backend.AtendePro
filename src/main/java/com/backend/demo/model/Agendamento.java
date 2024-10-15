package com.backend.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "Agendamento") 
@Data 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Agendamento {

    @Id
    private String id;

    private String servicos;

    private String promocao;

    private String profissional;

    private String diaAgendamento;

    private String horarioAgendamento;

    private List<User> clientes;
    
}
