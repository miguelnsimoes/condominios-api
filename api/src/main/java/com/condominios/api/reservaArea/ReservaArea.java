package com.condominios.api.reservaArea;

import com.condominios.api.areaComum.AreaComum;
import com.condominios.api.morador.Morador;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="reserva_area")
public class ReservaArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private AreaComum areaComum;

    @ManyToOne
    @JoinColumn(name = "morador_id")
    private Morador morador;

    public ReservaArea(){

    }

    public ReservaArea(LocalDate data, Long id, Morador morador) {
        this.data = data;
        this.id = id;
        this.morador = morador;
    }

    public AreaComum getAreaComum() {
        return areaComum;
    }

    public void setAreaComum(AreaComum areaComum) {
        this.areaComum = areaComum;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }
}
