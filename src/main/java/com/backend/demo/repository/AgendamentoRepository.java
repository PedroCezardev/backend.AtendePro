package com.backend.demo.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.backend.demo.model.Agendamento;

public interface AgendamentoRepository extends MongoRepository<Agendamento, String> {
    
    public List<Agendamento> findByServicos(String servicos);

    public List<Agendamento> findByPromocao(String promocao);

    public List<Agendamento> findByProfissional(String profissional);

    public List<Agendamento> findByDiaAgendamento(String diaAgendamento);

    public List<Agendamento> findByHorarioAgendamento(String horarioAgendamento);

}
