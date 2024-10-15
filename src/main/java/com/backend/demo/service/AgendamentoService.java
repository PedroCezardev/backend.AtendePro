package com.backend.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.demo.exception.InvalidAgendamentoException;
import com.backend.demo.model.Agendamento;
import com.backend.demo.repository.AgendamentoRepository;

@Service
public class AgendamentoService {
    
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public Agendamento insertAgendamento(Agendamento agendamento) {
        validateAgendamento(agendamento);
        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> getAllAgendamentos() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        if (agendamentos.isEmpty()) {
            throw new InvalidAgendamentoException("Nenhum agendamento encontrado");
        }
        return agendamentos;
    }

    public List<Agendamento> getByServicos(String servicos) {
        List<Agendamento> agendamentos = agendamentoRepository.findByServicos(servicos);
        if (agendamentos.isEmpty()) {
            throw new InvalidAgendamentoException("Nenhum servico no agendamento encontrado.");
        }
        return agendamentos;
    }

    public List<Agendamento> getByPromocao(String promocao) {
        List<Agendamento> agendamentos = agendamentoRepository.findByPromocao(promocao);
        if (agendamentos.isEmpty()) {
            throw new InvalidAgendamentoException("Nenhuma promocao no agendamento encontrada.");
        }
        return agendamentos;
    }

    public List<Agendamento> getByProfissional(String profissional) {
        List<Agendamento> agendamentos = agendamentoRepository.findByProfissional(profissional);
        if (agendamentos.isEmpty()) {
            throw new InvalidAgendamentoException("Nenhum profissional no agendamento encontrado.");
        }
        return agendamentos;
    }

    public List<Agendamento> getByDiaAgendamentos(String diaAgendamento) {
        List<Agendamento> agendamentos = agendamentoRepository.findByDiaAgendamento(diaAgendamento);
        if (agendamentos.isEmpty()) {
            throw new InvalidAgendamentoException("Nenhum dia no agendamento encontrado.");
        }
        return agendamentos;
    }

    public List<Agendamento> getByHorarioAgendamentos(String horarioAgendamento) {
        List<Agendamento> agendamentos = agendamentoRepository.findByHorarioAgendamento(horarioAgendamento);
        if (agendamentos.isEmpty()) {
            throw new InvalidAgendamentoException("Nenhum horario do agendamento encontrado.");
        }
        return agendamentos;
    }

    public void deleteById(String id) {
        if (!agendamentoRepository.existsById(id)) {
            throw new InvalidAgendamentoException("O agendamento não foi encontrado no sistema.");
        }
        agendamentoRepository.deleteById(id);
        System.out.println("O agendamento foi deletado com sucesso!");
    }

    public Agendamento updateAgendamentoById(String id, Agendamento agendamentoDetails) {

        if (agendamentoDetails == null) {
            throw new InvalidAgendamentoException("Os detalhes do Agendamento não podem ser nulos.");
        }

        Optional<Agendamento> optionalAgendamento = agendamentoRepository.findById(id);
        if (optionalAgendamento.isEmpty()) {
            throw new InvalidAgendamentoException("Agendamento não encontrado com id: " + id);
        }

        validateAgendamento(agendamentoDetails);
        Agendamento agendamento = optionalAgendamento.get();

        agendamento.setServicos(agendamentoDetails.getServicos());
        agendamento.setPromocao(agendamentoDetails.getPromocao());
        agendamento.setProfissional(agendamentoDetails.getProfissional());
        agendamento.setDiaAgendamento(agendamentoDetails.getDiaAgendamento());
        agendamento.setHorarioAgendamento(agendamentoDetails.getHorarioAgendamento());

        Agendamento updatedAgendamento = agendamentoRepository.save(agendamento);
        return updatedAgendamento;   
    }

    private void validateAgendamento(Agendamento agendamento) {
        if (agendamento.getServicos() == null || agendamento.getServicos().isEmpty() || agendamento.getServicos().length() > 50) {
            throw new InvalidAgendamentoException("O Servico do Agendamento não pode ser nulo, vazio ou maior que 50 caracteres.");
        }
        if (agendamento.getPromocao() == null || agendamento.getPromocao().isEmpty() || agendamento.getPromocao().length() > 50) {
            throw new InvalidAgendamentoException("A promocao do Agendamento não pode ser nulo, vazio ou maior que 50 caracteres.");
        }
        if (agendamento.getProfissional() == null || agendamento.getProfissional().isEmpty() || agendamento.getProfissional().length() > 50) {
            throw new InvalidAgendamentoException("O profissional do Agendamento não pode ser nulo, vazio ou maior que 50 caracteres.");
        }
        if (agendamento.getDiaAgendamento() == null || agendamento.getDiaAgendamento().isEmpty() || agendamento.getDiaAgendamento().length() > 50) {
            throw new InvalidAgendamentoException("O dia do Agendamento não pode ser nulo, vazio ou maior que 50 caracteres.");
        }
        if (agendamento.getHorarioAgendamento() == null || agendamento.getHorarioAgendamento().isEmpty() || agendamento.getHorarioAgendamento().length() > 50) {
            throw new InvalidAgendamentoException("O horario do Agendamento não pode ser nulo, vazio ou maior que 50 caracteres.");
        }
    }

}
