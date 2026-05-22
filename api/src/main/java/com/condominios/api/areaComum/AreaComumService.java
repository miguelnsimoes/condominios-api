package com.condominios.api.areaComum;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaComumService {

    private final AreaComumRepository areaComumRepository;

    public AreaComumService(AreaComumRepository areaComumRepository) {
        this.areaComumRepository = areaComumRepository;
    }

    public List<AreaComum> getAll(){
        return areaComumRepository.findAll();
    }

    public AreaComum save(AreaComum areaComum){
        return areaComumRepository.save(areaComum);
    }

    public void delete(Long id){
        //no futuro impedir a deleção de uma área comum se ela tiver reservas ativas atreladas a ela.
        areaComumRepository.deleteById(id);
    }

    public AreaComum findById(Long id){
        return areaComumRepository.findById(id).orElseThrow(() -> new RuntimeException("area nao encontrada"));
    }

    public AreaComum update(Long id, AreaComum areaComumAtualizada) {
        AreaComum areaComumExistente = findById(id);
        return areaComumRepository.save(areaComumExistente);
    }
}