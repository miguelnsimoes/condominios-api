package com.condominios.api.ocorrencia;

import com.condominios.api.infra.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;

    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository) {
        this.ocorrenciaRepository = ocorrenciaRepository;
    }

    public List<Ocorrencia> getAll() {
        return ocorrenciaRepository.findAll();
    }

    public Ocorrencia findById(Long id) {
        return ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ocorrência não encontrada"));
    }

    public Ocorrencia save(Ocorrencia ocorrencia) {
        return ocorrenciaRepository.save(ocorrencia);
    }

    public void delete(Long id) {
        ocorrenciaRepository.deleteById(id);
    }

    public Ocorrencia update(Long id, Ocorrencia ocorrenciaAtualizada) {
        Ocorrencia ocorrenciaExistente = findById(id);
        ocorrenciaExistente.setTitulo(ocorrenciaAtualizada.getTitulo());
        ocorrenciaExistente.setDescricao(ocorrenciaAtualizada.getDescricao());
        ocorrenciaExistente.setStatus(ocorrenciaAtualizada.getStatus());
        if (ocorrenciaAtualizada.getMorador() != null) {
            ocorrenciaExistente.setMorador(ocorrenciaAtualizada.getMorador());
        }
        return ocorrenciaRepository.save(ocorrenciaExistente);
    }
}
