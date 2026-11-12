package com.condominios.api.funcionario;

import com.condominios.api.pessoa.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "funcionario")
public class Funcionario extends Pessoa {

    private String cargo;
    private BigDecimal salario;


    public Funcionario(){
        super();
    }

    public Funcionario(String telefone, Integer idade, String nome, Long id, String cpf, String cargo, BigDecimal salario) {
        super(telefone, idade, nome, id, cpf);
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }


}
