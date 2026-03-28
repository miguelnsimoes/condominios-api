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

    public List<Encomenda> getAll() {
        return encomendaRepository.findAll();
    }

    public Encomenda findById(Long id) {
        return encomendaRepository.findById(id).orElseThrow(() -> new RuntimeException("Encomenda não encontrada"));
    }

    public Encomenda save(Encomenda encomenda) {
        return encomendaRepository.save(encomenda);
    }

    public void delete(Long id) {
        encomendaRepository.deleteById(id);
    }


    public Encomenda registrarRetirada(Long id) {
        Encomenda encomenda = encomendaRepository.findById(id).orElseThrow(() -> new RuntimeException("Encomenda não encontrada"));
        encomenda.setStatus("RETIRADA");
        encomenda.setDataRetirada(LocalDateTime.now());

        return encomendaRepository.save(encomenda);
    }
}