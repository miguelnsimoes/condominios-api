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

}

