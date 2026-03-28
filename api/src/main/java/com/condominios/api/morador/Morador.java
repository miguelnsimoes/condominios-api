package com.condominios.api.morador;

import com.condominios.api.apartamento.Apartamento;
import com.condominios.api.pessoa.Pessoa;
import jakarta.persistence.*;

@Entity
@Table(name = "morador")
public class Morador extends Pessoa {

    @ManyToOne
    @JoinColumn(name = "apartamento_id")
    private Apartamento apartamento;

    public Morador() {

    }

    public Morador(String telefone, Integer idade, String nome, Long id, String cpf, Apartamento apartamento) {
        super(telefone, idade, nome, id, cpf);
        this.apartamento = apartamento;
    }

    public Apartamento getApartamento() {
        return apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }
}
