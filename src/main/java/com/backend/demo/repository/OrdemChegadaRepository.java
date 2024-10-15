package com.backend.demo.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.backend.demo.model.OrdemChegada;

public interface OrdemChegadaRepository extends MongoRepository<OrdemChegada, String> {
    
    public List<OrdemChegada> findBySenhas(String senhas);

    public List<OrdemChegada> findByDiaAtendimento(String diaAtendimento);

    public List<OrdemChegada> findByHoraInicio(String horaInicio);

    public List<OrdemChegada> findByHoraTermino(String horaTermino);

}
