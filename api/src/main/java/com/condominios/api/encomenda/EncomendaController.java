package com.condominios.api.encomenda;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encomendas")
public class EncomendaController {

    private final EncomendaService encomendaService;

    public EncomendaController(EncomendaService encomendaService) {
        this.encomendaService = encomendaService;
    }

    @GetMapping
    public List<Encomenda> getAll(){
        return encomendaService.getAll();
    }

    @GetMapping("/{id}")
    public Encomenda getId(@PathVariable Long id){
        return encomendaService.findById(id);
    }

    @PostMapping
    public Encomenda create(@RequestBody Encomenda encomenda){
        return encomendaService.save(encomenda);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        encomendaService.delete(id);
    }

    @PutMapping("/{id}/retirada")
    public Encomenda registrarRetirada(@PathVariable Long id){
        return encomendaService.registrarRetirada(id);
    }

}