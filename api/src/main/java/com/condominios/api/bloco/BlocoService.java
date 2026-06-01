package com.condominios.api.bloco;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlocoService {

    private final BlocoRepository blocoRepository;

    public BlocoService(BlocoRepository blocoRepository) {
        this.blocoRepository = blocoRepository;
    }

    public List<Bloco> getAll(){
        return blocoRepository.findAll();
    }

    public Bloco save(Bloco bloco){
        return blocoRepository.save(bloco);
    }

    public void delete(Long id){
        Bloco bloco = findById(id);

        if (bloco.getApartamentos() != null && !bloco.getApartamentos().isEmpty()) {
            throw new RuntimeException("Não é possível deletar o bloco porque existem apartamentos vinculados a ele.");
        }

        blocoRepository.deleteById(id);
    }

    public Bloco findById(Long id){
        return blocoRepository.findById(id).orElseThrow(() -> new RuntimeException("bloco nao encontrado"));
    }

    public Bloco update(Long id, Bloco blocoAtualizado) {
        Bloco blocoExistente = findById(id);
        blocoExistente.setNome(blocoAtualizado.getNome());
        return blocoRepository.save(blocoExistente);
    }



}

