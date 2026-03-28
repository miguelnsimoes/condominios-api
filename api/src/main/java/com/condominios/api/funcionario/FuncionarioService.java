package com.condominios.api.funcionario;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> getAll(){
        return funcionarioRepository.findAll();
    }

    public Funcionario findById(Long id){
        return funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("funcionario nao encontrado"));
    }

    public Funcionario save(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    public void delete(Long id){
        funcionarioRepository.deleteById(id);
    }

}
