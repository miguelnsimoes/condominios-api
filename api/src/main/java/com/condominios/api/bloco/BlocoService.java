package com.condominios.api.bloco;

import com.condominios.api.infra.exception.BusinessException;
import com.condominios.api.infra.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlocoService {

    private final BlocoRepository blocoRepository;

    public BlocoService(BlocoRepository blocoRepository) {
        this.blocoRepository = blocoRepository;
    }

    public List<Bloco> getAll() {
        return blocoRepository.findAll();
    }

    public Bloco save(Bloco bloco) {
        return blocoRepository.save(bloco);
    }

    public void delete(Long id) {
        Bloco bloco = findById(id);

        if (bloco.getApartamentos() != null && !bloco.getApartamentos().isEmpty()) {
            throw new BusinessException("Não é possível deletar o bloco porque existem apartamentos vinculados a ele");
        }

        blocoRepository.deleteById(id);
    }

    public Bloco findById(Long id) {
        return blocoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bloco não encontrado"));
    }

    public Bloco update(Long id, Bloco blocoAtualizado) {
        Bloco blocoExistente = findById(id);
        blocoExistente.setNome(blocoAtualizado.getNome());
        return blocoRepository.save(blocoExistente);
    }
}
