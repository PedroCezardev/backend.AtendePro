package com.backend.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "User") 
@Data 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private String id;

    private String username;

    private String email;

    private String password;

    private String telefone;

    private List<OrdemChegada> diaAtendimento;

    private List<Agendamento> agendamentos;
    
}
