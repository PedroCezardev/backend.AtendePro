package com.backend.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.demo.exception.InvalidAgendamentoException;
import com.backend.demo.model.Agendamento;
import com.backend.demo.service.AgendamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value = "/api/agendamento")
public class AgendamentoController {
    
    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping("/add")
    public Agendamento inserirAgendamento(@RequestBody Agendamento agendamento) {
        try {
            return agendamentoService.insertAgendamento(agendamento);
        } catch (InvalidAgendamentoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list")
    public List<Agendamento> getAllAgendamentos() {
        try {
            return agendamentoService.getAllAgendamentos();
        } catch (InvalidAgendamentoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list/servicos/{servicos}")
    public List<Agendamento> getServicos(@PathVariable String servicos) {
       try {
            return agendamentoService.getByServicos(servicos);
        } catch (InvalidAgendamentoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @GetMapping("/list/promocao/{promocao}")
    public List<Agendamento> getPromocao(@PathVariable String promocao) {
        try {
            return agendamentoService.getByPromocao(promocao);
        } catch (InvalidAgendamentoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list/profissional/{profissional}")
    public List<Agendamento> getProfissional(@PathVariable String profissional) {
        try {
            return agendamentoService.getByProfissional(profissional);
        } catch (InvalidAgendamentoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list/diaAgendamento/{diaAgendamento}")
    public List<Agendamento> getDiaAgendamento(@PathVariable String diaAgendamento) {
        try {
            return agendamentoService.getByDiaAgendamentos(diaAgendamento);
        } catch (InvalidAgendamentoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list/horarioAgendamento/{horarioAgendamento}")
    public List<Agendamento> getHorarioAgendamentos(@PathVariable String horarioAgendamento) {
        try {
            return agendamentoService.getByHorarioAgendamentos(horarioAgendamento);
        } catch (InvalidAgendamentoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteAgendamento(@PathVariable String id) {
        try {
            agendamentoService.deleteById(id);
        } catch (InvalidAgendamentoException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAgendamento(@PathVariable String id, @RequestBody Agendamento agendamentoDetails) {
        try {
            Agendamento updatedAgendamento = agendamentoService.updateAgendamentoById(id, agendamentoDetails);
            return ResponseEntity.ok(updatedAgendamento);
        } catch (InvalidAgendamentoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado.");
        }
    }

}
