package com.condominios.api.reservaArea;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas-de-area")
public class ReservaAreaController {
    private final ReservaAreaService reservaAreaService;

    public ReservaAreaController(ReservaAreaService reservaAreaService) {
        this.reservaAreaService = reservaAreaService;
    }

    @GetMapping
    public List<ReservaArea> getAll(){
        return reservaAreaService.getAll();
    }

    @GetMapping("/{id}")
    public ReservaArea getById(@PathVariable Long id) {
        return reservaAreaService.findById(id);
    }

    @PostMapping
    public ReservaArea create(@RequestBody ReservaArea reservaArea){
        return reservaAreaService.save(reservaArea);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        reservaAreaService.delete(id);
    }

}
