package com.condominios.api.morador;

import com.condominios.api.apartamento.ApartamentoRepository;
import com.condominios.api.infra.exception.BusinessException;
import com.condominios.api.infra.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoradorService {

    private final MoradorRepository moradorRepository;
    private final ApartamentoRepository apartamentoRepository;

    public MoradorService(MoradorRepository moradorRepository, ApartamentoRepository apartamentoRepository) {
        this.moradorRepository = moradorRepository;
        this.apartamentoRepository = apartamentoRepository;
    }

    public List<Morador> getAll() {
        return moradorRepository.findAll();
    }

    public List<Morador> findByApartamentoId(Long apartamentoId) {
        validarApartamento(apartamentoId);
        return moradorRepository.findByApartamentoId(apartamentoId);
    }

    public Morador findById(Long id) {
        return moradorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Morador não encontrado"));
    }

    public Morador save(Morador morador) {
        validarApartamentoObrigatorio(morador);
        return moradorRepository.save(morador);
    }

    public void delete(Long id) {
        moradorRepository.deleteById(id);
    }

    public Morador update(Long id, Morador moradorAtualizado) {
        Morador moradorExistente = findById(id);
        moradorExistente.setNome(moradorAtualizado.getNome());
        moradorExistente.setCpf(moradorAtualizado.getCpf());
        moradorExistente.setTelefone(moradorAtualizado.getTelefone());
        moradorExistente.setIdade(moradorAtualizado.getIdade());
        if (moradorAtualizado.getApartamento() != null) {
            validarApartamento(moradorAtualizado.getApartamento().getId());
            moradorExistente.setApartamento(moradorAtualizado.getApartamento());
        }
        return moradorRepository.save(moradorExistente);
    }

    private void validarApartamentoObrigatorio(Morador morador) {
        if (morador.getApartamento() == null || morador.getApartamento().getId() == null) {
            throw new BusinessException("Morador deve estar vinculado a um apartamento");
        }
        validarApartamento(morador.getApartamento().getId());
    }

    private void validarApartamento(Long apartamentoId) {
        if (!apartamentoRepository.existsById(apartamentoId)) {
            throw new ResourceNotFoundException("Apartamento não encontrado");
        }
    }
}
