package com.condominios.api.ocorrencia;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {

    private final OcorrenciaService ocorrenciaService;

    public OcorrenciaController(OcorrenciaService ocorrenciaService) {
        this.ocorrenciaService = ocorrenciaService;
    }

    @GetMapping
    public List<Ocorrencia> getAll() {
        return ocorrenciaService.getAll();
    }

    @GetMapping("/{id}")
    public Ocorrencia getById(@PathVariable Long id) {
        return ocorrenciaService.findById(id);
    }

    @PostMapping
    public Ocorrencia create(@RequestBody Ocorrencia ocorrencia) {
        return ocorrenciaService.save(ocorrencia);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ocorrenciaService.delete(id);
    }
}