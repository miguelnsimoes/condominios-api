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
        apartamentoRepository.deleteById(id);
    }


    public Apartamento findById(Long id){
        return apartamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("apartamento nao encontrada"));
    }
}
