package com.backend.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.demo.exception.InvalidOrdemChegadaException;
import com.backend.demo.model.OrdemChegada;
import com.backend.demo.repository.OrdemChegadaRepository;

@Service
public class OrdemChegadaService {

    @Autowired
    private OrdemChegadaRepository ordemchegadaRepository;

    public OrdemChegada insertOrdemChegada(OrdemChegada ordemChegada) {
        validateOrdemChegada(ordemChegada);
        return ordemchegadaRepository.save(ordemChegada);
    }

    public List<OrdemChegada> getAllOrdemChegada() {
        List<OrdemChegada> ordemChegada = ordemchegadaRepository.findAll();
        if (ordemChegada.isEmpty()) {
            throw new InvalidOrdemChegadaException("Nenhum ordem de chegada nao encontrada");
        }
        return ordemChegada;
    }

    public List<OrdemChegada> getBySenhas(String senhas) {
        List<OrdemChegada> ordemchegada = ordemchegadaRepository.findBySenhas(senhas);
        if (ordemchegada.isEmpty()) {
            throw new InvalidOrdemChegadaException("Nenhuma senha na ordem de chegada encontrada.");
        }
        return ordemchegada;
    }

    public List<OrdemChegada> getByDiaAtendimento(String diaAtendimento) {
        List<OrdemChegada> ordemchegada = ordemchegadaRepository.findByDiaAtendimento(diaAtendimento);
        if(ordemchegada.isEmpty()) {
            throw new InvalidOrdemChegadaException("Nenhum dia de atendimento encontrado.");
        }
        return ordemchegada;
    }

    public List<OrdemChegada> getByHoraInicio(String horaInicio) {
        List<OrdemChegada> ordemchegada = ordemchegadaRepository.findByHoraInicio(horaInicio);
        if(ordemchegada.isEmpty()) {
            throw new InvalidOrdemChegadaException("Nenhuma hora de inicio do atendimento encontrada.");
        }
        return ordemchegada;
    }

    public List<OrdemChegada> getByHoraTermino(String horaTermino) {
        List<OrdemChegada> ordemchegada = ordemchegadaRepository.findByHoraTermino(horaTermino);
        if(ordemchegada.isEmpty()) {
            throw new InvalidOrdemChegadaException("Nenhuma hora de termino do atendimento encontrado.");
        }
        return ordemchegada;
    }

    public void deleteById(String Id) {
        if (!ordemchegadaRepository.existsById(Id)) {
            throw new InvalidOrdemChegadaException("A ordem de chegada nao foi encontrada.");
        }
        ordemchegadaRepository.deleteById(Id);
        System.out.println("A ordem de chegada foi deletada com sucesso!");
    }

    public OrdemChegada updateOrdemChegadaById(String id, OrdemChegada ordemChegadaDetails) {

        if (ordemChegadaDetails == null) {
            throw new InvalidOrdemChegadaException("Os detalhes da ordem de chegada nao podem ser nulos.");
        }

        Optional<OrdemChegada> optionalOrdemchegada = ordemchegadaRepository.findById(id);
        if (optionalOrdemchegada.isEmpty()) {
            throw new InvalidOrdemChegadaException("Ordem de chegada nao encontrado com id: " + id);
        }

        validateOrdemChegada(ordemChegadaDetails);
        OrdemChegada ordemChegada = optionalOrdemchegada.get();

        ordemChegada.setSenhas(ordemChegadaDetails.getSenhas());
        ordemChegada.setDiaAtendimento(ordemChegadaDetails.getDiaAtendimento());
        ordemChegada.setHoraInicio(ordemChegadaDetails.getHoraInicio());
        ordemChegada.setHoraTermino(ordemChegadaDetails.getHoraTermino());

        OrdemChegada updatedOrdemChegada = ordemchegadaRepository.save(ordemChegada);
        return updatedOrdemChegada;
    }

    private void validateOrdemChegada(OrdemChegada ordemchegada) {
        if (ordemchegada.getSenhas() == null || ordemchegada.getSenhas().isEmpty()) {
            throw new InvalidOrdemChegadaException("A senha não pode ser nula ou vazia");
        }
        if (ordemchegada.getDiaAtendimento() == null || ordemchegada.getDiaAtendimento().isEmpty()) {
            throw new InvalidOrdemChegadaException("O dia de atendimento não pode ser nulo ou vazio");
        }
        if (ordemchegada.getHoraInicio() == null) {
            throw new InvalidOrdemChegadaException("A hora de Inicio não pode ser nulo");
        }
        if (ordemchegada.getHoraTermino() == null) {
            throw new InvalidOrdemChegadaException("A hora de Termino não pode ser nulo");
        }
    }
    
}
