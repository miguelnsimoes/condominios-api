package com.condominios.api.apartamento;

import com.condominios.api.bloco.Bloco;
import jakarta.persistence.*;

@Entity
@Table
public class Apartamento {
    @Id
    private Long id;
    private Integer numero;
    @ManyToOne
    @JoinColumn(name = "bloco_id")
    private Bloco bloco;

    public Apartamento(){

    }

    public Apartamento(Bloco bloco, Long id, Integer numero) {
        this.bloco = bloco;
        this.id = id;
        this.numero = numero;
    }

    public Bloco getBloco() {
        return bloco;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
