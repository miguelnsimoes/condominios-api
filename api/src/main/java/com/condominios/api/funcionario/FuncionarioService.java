package com.condominios.api.funcionario;

import com.condominios.api.infra.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> getAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado"));
    }

    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void delete(Long id) {
        funcionarioRepository.deleteById(id);
    }

    public Funcionario update(Long id, Funcionario funcionarioAtualizado) {
        Funcionario funcionarioExistente = findById(id);
        funcionarioExistente.setNome(funcionarioAtualizado.getNome());
        funcionarioExistente.setCpf(funcionarioAtualizado.getCpf());
        funcionarioExistente.setTelefone(funcionarioAtualizado.getTelefone());
        funcionarioExistente.setIdade(funcionarioAtualizado.getIdade());
        funcionarioExistente.setCargo(funcionarioAtualizado.getCargo());
        funcionarioExistente.setSalario(funcionarioAtualizado.getSalario());
        return funcionarioRepository.save(funcionarioExistente);
    }
}
