package com.condominios.api.bloco;

import com.condominios.api.apartamento.ApartamentoRepository;
import com.condominios.api.apartamento.ApartamentoService;
import com.condominios.api.infra.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlocoService {

    private final BlocoRepository blocoRepository;
    private final ApartamentoRepository apartamentoRepository;
    private final ApartamentoService apartamentoService;

    public BlocoService(
            BlocoRepository blocoRepository,
            ApartamentoRepository apartamentoRepository,
            ApartamentoService apartamentoService) {
        this.blocoRepository = blocoRepository;
        this.apartamentoRepository = apartamentoRepository;
        this.apartamentoService = apartamentoService;
    }

    public List<Bloco> getAll() {
        return blocoRepository.findAll();
    }

    public Bloco save(Bloco bloco) {
        return blocoRepository.save(bloco);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        apartamentoRepository.findByBlocoId(id).forEach(a -> apartamentoService.delete(a.getId()));
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
