package com.condominios.api.relatorio;

import com.condominios.api.encomenda.Encomenda;
import com.condominios.api.encomenda.EncomendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioService {

    private static final String STATUS_PENDENTE = "PENDENTE";

    private final EncomendaRepository encomendaRepository;

    public RelatorioService(EncomendaRepository encomendaRepository) {
        this.encomendaRepository = encomendaRepository;
    }

    public List<Encomenda> encomendasPendentes() {
        return encomendaRepository.findByStatus(STATUS_PENDENTE);
    }
}
