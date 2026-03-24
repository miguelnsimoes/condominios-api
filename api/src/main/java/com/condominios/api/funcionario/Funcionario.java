package com.condominios.api.funcionario;

import com.condominios.api.pessoa.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionario")
public class Funcionario extends Pessoa {

    private String cargo;
    private Double salario;

    public Funcionario(){
        super();
    }

    public Funcionario(Long id, String nome, String cpf, Integer idade, String cargo, Double salario) {
        super(id, nome, cpf, idade);
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
}
