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
    public List<Apartamento> getAll(@RequestParam(required = false) Long blocoId){
        if (blocoId != null) {
            return apartamentoService.findByBlocoId(blocoId);
        }
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

    @PutMapping("/{id}")
    public Apartamento update(@PathVariable Long id, @RequestBody Apartamento apartamento) {
        return apartamentoService.update(id, apartamento);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        apartamentoService.delete(id);
    }
}