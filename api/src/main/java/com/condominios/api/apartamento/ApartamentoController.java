package com.condominios.api.apartamento;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/apartamentos")
public class ApartamentoController {
    private final ApartamentoService apartamentoService;

    public ApartamentoController(ApartamentoService apartamentoService) {
        this.apartamentoService = apartamentoService;
    }


    @GetMapping
    public List<Apartamento> getAll(){
        return apartamentoService.getAll();
    }

    @GetMapping("/{id}")
    public Apartamento getId(@PathVariable Long id){
        return apartamentoService.findById(id);
    }

    @PostMapping
    public Apartamento create(@RequestBody Apartamento apartamento){
        return apartamentoService.save(apartamento);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        apartamentoService.delete(id);
    }
}
