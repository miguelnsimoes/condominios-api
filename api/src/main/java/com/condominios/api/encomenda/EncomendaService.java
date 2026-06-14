package com.condominios.api.encomenda;

import com.condominios.api.apartamento.ApartamentoRepository;
import com.condominios.api.infra.exception.BusinessException;
import com.condominios.api.infra.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EncomendaService {

    private final EncomendaRepository encomendaRepository;
    private final ApartamentoRepository apartamentoRepository;

    public EncomendaService(EncomendaRepository encomendaRepository, ApartamentoRepository apartamentoRepository) {
        this.encomendaRepository = encomendaRepository;
        this.apartamentoRepository = apartamentoRepository;
    }

    public List<Encomenda> getAll() {
        return encomendaRepository.findAll();
    }

    public Encomenda findById(Long id) {
        return encomendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Encomenda não encontrada"));
    }

    public Encomenda save(Encomenda encomenda) {
        if (encomenda.getApartamento() == null || encomenda.getApartamento().getId() == null) {
            throw new BusinessException("Encomenda deve estar vinculada a um apartamento");
        }
        if (!apartamentoRepository.existsById(encomenda.getApartamento().getId())) {
            throw new ResourceNotFoundException("Apartamento não encontrado");
        }
        return encomendaRepository.save(encomenda);
    }

    public void delete(Long id) {
        encomendaRepository.deleteById(id);
    }

    public Encomenda registrarRetirada(Long id) {
        Encomenda encomenda = findById(id);
        encomenda.setStatus("RETIRADA");
        encomenda.setDataRetirada(LocalDateTime.now());
        return encomendaRepository.save(encomenda);
    }
}
