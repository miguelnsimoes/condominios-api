package com.condominios.api.morador;

import com.condominios.api.funcionario.Funcionario;
import com.condominios.api.funcionario.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MoradorService {

    private final MoradorRepository moradorRepository;

    public MoradorService(MoradorRepository moradorRepository) {
        this.moradorRepository = moradorRepository;
    }

    public List<Morador> getAll(){
        return moradorRepository.findAll();
    }

    public Morador findById(Long id){
        return moradorRepository.findById(id).orElseThrow(() -> new RuntimeException("Morador nao encontrado"));
    }

    public Morador save(Morador morador){
        return moradorRepository.save(morador);
    }

    public void delete(Long id){
        moradorRepository.deleteById(id);
    }

}
