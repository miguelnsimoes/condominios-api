package com.condominios.api.bloco;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
