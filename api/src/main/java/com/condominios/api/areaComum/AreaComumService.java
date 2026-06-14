package com.condominios.api.areaComum;

import com.condominios.api.infra.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaComumService {

    private final AreaComumRepository areaComumRepository;

    public AreaComumService(AreaComumRepository areaComumRepository) {
        this.areaComumRepository = areaComumRepository;
    }

    public List<AreaComum> getAll() {
        return areaComumRepository.findAll();
    }

    public AreaComum save(AreaComum areaComum) {
        return areaComumRepository.save(areaComum);
    }

    public void delete(Long id) {
        areaComumRepository.deleteById(id);
    }

    public AreaComum findById(Long id) {
        return areaComumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Área comum não encontrada"));
    }

    public AreaComum update(Long id, AreaComum areaComumAtualizada) {
        AreaComum areaComumExistente = findById(id);
        areaComumExistente.setNome(areaComumAtualizada.getNome());
        return areaComumRepository.save(areaComumExistente);
    }
}
