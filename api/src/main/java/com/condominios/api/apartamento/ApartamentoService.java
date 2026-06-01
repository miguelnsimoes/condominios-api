package com.condominios.api.apartamento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartamentoService {
    private final ApartamentoRepository apartamentoRepository;

    public ApartamentoService(ApartamentoRepository apartamentoRepository) {
        this.apartamentoRepository = apartamentoRepository;
    }

    public List<Apartamento> getAll(){
        return apartamentoRepository.findAll();
    }

    public Apartamento save(Apartamento apartamento){
        return apartamentoRepository.save(apartamento);
    }

    public void delete(Long id){
        // no futuro vale a pena colocar um "if"
        // aqui para impedir a deleção se o apartamento tiver moradores vinculados
        apartamentoRepository.deleteById(id);
    }

    public Apartamento findById(Long id){
        return apartamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("apartamento nao encontrada"));
    }

    public Apartamento update(Long id, Apartamento apartamentoAtualizado) {
        Apartamento apartamentoExistente = findById(id);
        return apartamentoRepository.save(apartamentoExistente);
    }

    public List<Apartamento> findByBlocoId(Long blocoId){
        return apartamentoRepository.findByBlocoId(blocoId);
    }
}