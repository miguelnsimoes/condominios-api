package com.condominios.api.morador;

import com.condominios.api.funcionario.Funcionario;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MoradorController {

    private final MoradorService moradorService;

    public MoradorController(MoradorService moradorService) {
        this.moradorService = moradorService;
    }


    @GetMapping
    public List<Morador> getAll(){
        return moradorService.getAll();
    }

    @GetMapping("/{id}")
    public Morador getById(@PathVariable Long id) {
        return moradorService.findById(id);
    }

    @PostMapping
    public Morador create(@RequestBody Morador morador){
        return moradorService.save(morador);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        moradorService.delete(id);
    }

}
