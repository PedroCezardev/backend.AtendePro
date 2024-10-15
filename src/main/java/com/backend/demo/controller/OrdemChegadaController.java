package com.backend.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.demo.exception.InvalidOrdemChegadaException;
import com.backend.demo.model.OrdemChegada;
import com.backend.demo.service.OrdemChegadaService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "/api/ordemchegada")
public class OrdemChegadaController {

    @Autowired
    private OrdemChegadaService ordemchegadaService;

    @PostMapping("/add")
    public OrdemChegada inserirOrdemChegada(@RequestBody OrdemChegada ordemchegada) {
        try {
            return ordemchegadaService.insertOrdemChegada(ordemchegada);
        } catch (InvalidOrdemChegadaException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list")
    public List<OrdemChegada> getAllOrdemChegadas() {
        try {
            return ordemchegadaService.getAllOrdemChegada();
        } catch (InvalidOrdemChegadaException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list/senhas/{senhas}")
    public List<OrdemChegada> getSenhas(@PathVariable String senhas) {
        try {
            return ordemchegadaService.getBySenhas(senhas);
        } catch (InvalidOrdemChegadaException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list/diaAtendimento/{diaAtendimento}")
    public List<OrdemChegada> getDiaAtendimento(@PathVariable String diaAtendimento) {
        try {
            return ordemchegadaService.getByDiaAtendimento(diaAtendimento);
        } catch (InvalidOrdemChegadaException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list/horainicio/{horaInicio}")
    public List<OrdemChegada> getHoraInicio(@PathVariable String horaInicio) {
        try {
            return ordemchegadaService.getByHoraInicio(horaInicio);
        } catch (InvalidOrdemChegadaException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/list/horatermino/{horatermino}")
    public List<OrdemChegada> getHoraTermino(@PathVariable String horaTermino) {
        try {
            return ordemchegadaService.getByHoraTermino(horaTermino);
        } catch (InvalidOrdemChegadaException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrdemChegadas(@PathVariable String id) {
        try {
            ordemchegadaService.deleteById(id);
        } catch (InvalidOrdemChegadaException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrdemChegada(@PathVariable String id, @RequestBody OrdemChegada ordemChegadaDetails) {
        try {
            OrdemChegada updatedOrdemchegada = ordemchegadaService.updateOrdemChegadaById(id, ordemChegadaDetails);
            return ResponseEntity.ok(updatedOrdemchegada);
        } catch (InvalidOrdemChegadaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado.");
        }
    }
    
}
