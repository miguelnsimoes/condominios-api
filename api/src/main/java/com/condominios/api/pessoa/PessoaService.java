package com.condominios.api.pessoa;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> getAll() {
        return pessoaRepository.findAll();
    }
}