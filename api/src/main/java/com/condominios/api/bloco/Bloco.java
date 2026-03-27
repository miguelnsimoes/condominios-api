package com.condominios.api.bloco;
import jakarta.persistence.*;

@Entity
@Table(name = "bloco")
public class Bloco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;


    public Bloco(){

    }

    public Bloco(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome; 
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
