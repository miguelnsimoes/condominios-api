package com.condominios.api.funcionario;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }


    @GetMapping
    public List<Funcionario> getAll(){
        return funcionarioService.getAll();
    }

    @GetMapping("/{id}")
    public Funcionario getById(@PathVariable Long id) {
        return funcionarioService.findById(id);
    }

    @PostMapping
    public Funcionario create(@RequestBody Funcionario funcionario){
        return funcionarioService.save(funcionario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        funcionarioService.delete(id);
    }
}
