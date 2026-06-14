package com.condominios.api.reservaArea;

import com.condominios.api.areaComum.AreaComum;
import com.condominios.api.morador.Morador;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reserva_area")
public class ReservaArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private AreaComum areaComum;

    @ManyToOne
    @JoinColumn(name = "morador_id")
    private Morador morador;

    public ReservaArea() {
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

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
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
