package com.condominios.api.morador;

import com.condominios.api.pessoa.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.Query;
import jakarta.persistence.Table;

@Entity
@Table(name = "morador")
public class Morador extends Pessoa {

    private Integer apartamento;

    public Morador() {

    }

    public Morador(String telefone, Integer idade, String nome, Long id, String cpf, Integer apartamento) {
        super(telefone, idade, nome, id, cpf);
        this.apartamento = apartamento;
    }

    public Integer getApartamento() {
        return apartamento;
    }

    public void setApartamento(Integer apartamento) {
        this.apartamento = apartamento;
    }


}
