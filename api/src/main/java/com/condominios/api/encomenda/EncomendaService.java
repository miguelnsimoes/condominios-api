package com.condominios.api.encomenda;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EncomendaService {
    private final EncomendaRepository encomendaRepository;

    public EncomendaService(EncomendaRepository encomendaRepository) {
        this.encomendaRepository = encomendaRepository;
    }

    public List<Encomenda> getAll(){
        return encomendaRepository.findAll();
    }

    public Encomenda findById(Long id){
        return encomendaRepository.findById(id).orElseThrow(() -> new RuntimeException("encomenda nao encontrado"));
    }

    public Encomenda save(Encomenda funcionario){
        return encomendaRepository.save(funcionario);
    }

    public void delete(Long id){
        encomendaRepository.deleteById(id);
    }

    public void registrarRetirada(Encomenda encomenda, Long id){
         encomenda.setStatus("RETIRADA");
         encomenda.setDataRetirada(LocalDateTime.now());
    }


}
