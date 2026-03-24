package com.condominios.api.bloco;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blocos")
public class BlocoController {

    private final BlocoService blocoService; //injecao de dependencia

    public BlocoController(BlocoService blocoService) {
        this.blocoService = blocoService;
    }

    @GetMapping
    public List<Bloco> getAll(){
        return blocoService.getAll();
    }

    @PostMapping
    public Bloco create(@RequestBody Bloco bloco){
        return blocoService.save(bloco);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        blocoService.delete(id);
    }
}
