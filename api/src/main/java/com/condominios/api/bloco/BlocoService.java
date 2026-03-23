package com.condominios.api.bloco;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlocoService {

    private final BlocoRepository blocoRepository;

    public BlocoService(BlocoRepository blocoRepository) {
        this.blocoRepository = blocoRepository;
    }

    //listar
    public List<Bloco> getAll(){
        return blocoRepository.findAll();
    }

    //criar
    public Bloco save(Bloco bloco){
        return blocoRepository.save(bloco);
    }

    //deletar, implementar regra de negocio pq é PK e pode dar bug
    public void delete(Long id){
        blocoRepository.deleteById(id);
    }

}

