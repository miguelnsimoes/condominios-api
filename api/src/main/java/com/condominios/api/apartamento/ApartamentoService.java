package com.condominios.api.apartamento;

import com.condominios.api.bloco.BlocoRepository;
import com.condominios.api.encomenda.EncomendaRepository;
import com.condominios.api.infra.exception.DuplicateResourceException;
import com.condominios.api.infra.exception.ResourceNotFoundException;
import com.condominios.api.morador.MoradorRepository;
import com.condominios.api.morador.MoradorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApartamentoService {

    private final ApartamentoRepository apartamentoRepository;
    private final BlocoRepository blocoRepository;
    private final MoradorRepository moradorRepository;
    private final MoradorService moradorService;
    private final EncomendaRepository encomendaRepository;

    public ApartamentoService(
            ApartamentoRepository apartamentoRepository,
            BlocoRepository blocoRepository,
            MoradorRepository moradorRepository,
            MoradorService moradorService,
            EncomendaRepository encomendaRepository) {
        this.apartamentoRepository = apartamentoRepository;
        this.blocoRepository = blocoRepository;
        this.moradorRepository = moradorRepository;
        this.moradorService = moradorService;
        this.encomendaRepository = encomendaRepository;
    }

    public List<Apartamento> getAll() {
        return apartamentoRepository.findAll();
    }

    public Apartamento save(Apartamento apartamento) {
        validarBloco(apartamento);
        validarDuplicidade(apartamento);
        return apartamentoRepository.save(apartamento);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        encomendaRepository.deleteByApartamento_Id(id);
        moradorRepository.findByApartamentoId(id).forEach(m -> moradorService.delete(m.getId()));
        apartamentoRepository.deleteById(id);
    }

    public Apartamento findById(Long id) {
        return apartamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Apartamento não encontrado"));
    }

    public Apartamento update(Long id, Apartamento apartamentoAtualizado) {
        Apartamento apartamentoExistente = findById(id);
        apartamentoAtualizado.setId(id);
        validarBloco(apartamentoAtualizado);
        validarDuplicidade(apartamentoAtualizado);
        apartamentoExistente.setNumero(apartamentoAtualizado.getNumero());
        apartamentoExistente.setBloco(apartamentoAtualizado.getBloco());
        return apartamentoRepository.save(apartamentoExistente);
    }

    public List<Apartamento> findByBlocoId(Long blocoId) {
        if (!blocoRepository.existsById(blocoId)) {
            throw new ResourceNotFoundException("Bloco não encontrado");
        }
        return apartamentoRepository.findByBlocoId(blocoId);
    }

    private void validarBloco(Apartamento apartamento) {
        if (apartamento.getBloco() == null || apartamento.getBloco().getId() == null) {
            throw new ResourceNotFoundException("Bloco não encontrado");
        }
        if (!blocoRepository.existsById(apartamento.getBloco().getId())) {
            throw new ResourceNotFoundException("Bloco não encontrado");
        }
    }

    private void validarDuplicidade(Apartamento apartamento) {
        boolean duplicado = apartamento.getId() == null
                ? apartamentoRepository.existsByNumeroAndBlocoId(apartamento.getNumero(), apartamento.getBloco().getId())
                : apartamentoRepository.existsByNumeroAndBlocoIdAndIdNot(
                        apartamento.getNumero(), apartamento.getBloco().getId(), apartamento.getId());

        if (duplicado) {
            throw new DuplicateResourceException("Já existe um apartamento com este número neste bloco");
        }
    }
}
