package com.condominios.api.areaComum;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AreaComumController {
    private final AreaComumService areaComumService;

    public AreaComumController(AreaComumService areaComumService) {
        this.areaComumService = areaComumService;
    }


    @GetMapping
    public List<AreaComum> getAll(){
        return areaComumService.getAll();
    }

    @GetMapping("/{id}")
    public AreaComum getId(@PathVariable Long id){
        return areaComumService.findById(id);
    }

    @PostMapping
    public AreaComum create(@RequestBody AreaComum areaComum){
        return areaComumService.save(areaComum);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        areaComumService.delete(id);
    }

}
