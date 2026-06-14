package com.condominios.api.morador;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moradores")
public class MoradorController {

    private final MoradorService moradorService;

    public MoradorController(MoradorService moradorService) {
        this.moradorService = moradorService;
    }

    @GetMapping
    public List<Morador> getAll(@RequestParam(required = false) Long apartamentoId){
        if (apartamentoId != null) {
            return moradorService.findByApartamentoId(apartamentoId);
        }
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

    @PutMapping("/{id}")
    public Morador update(@PathVariable Long id, @RequestBody Morador morador) {
        return moradorService.update(id, morador);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        moradorService.delete(id);
    }
}