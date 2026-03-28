package com.condominios.api.apartamento;

import  com.condominios.api.bloco.Bloco;
import jakarta.persistence.*;

@Entity
@Table
public class Apartamento {
    @Id
    private Long id;
    private String numero;
    @ManyToOne
    @JoinColumn(name = "bloco_id")
    private Bloco bloco;

    public Apartamento(){

    }

    public Apartamento(Bloco bloco, Long id, String numero) {
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
