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
    private String telefone;

    public Funcionario(){
        super();
    }

    public Funcionario(Long id, String nome, String cpf, Integer idade, String cargo, BigDecimal salario, String telefone) {
        super(id, nome, cpf, idade);
        this.cargo = cargo;
        this.salario = salario;
        this.telefone = telefone;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
