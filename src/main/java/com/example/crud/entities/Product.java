package com.example.crud.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;


@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Double price;
    @CreationTimestamp
    private Instant tempoDeCriacao;
    @UpdateTimestamp
    private Instant tempoDeAtualizao;

    public Product() {
    }
    public Product(UUID productId, String name, String code, String description, Double price, Instant tempoDeCriacao, Instant tempoDeAtualizao) {
        this.productId = productId;
        this.name = name;
        this.code = code;
        this.description = description;
        this.price = price;
        this.tempoDeCriacao = tempoDeCriacao;
        this.tempoDeAtualizao = tempoDeAtualizao;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setId(UUID productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Instant getTempoDeCriacao() {
        return tempoDeCriacao;
    }

    public void setTempoDeCriacao(Instant tempoDeCriacao) {
        this.tempoDeCriacao = tempoDeCriacao;
    }

    public Instant getTempoDeAtualizao() {
        return tempoDeAtualizao;
    }

    public void setTempoDeAtualizao(Instant tempoDeAtualizao) {
        this.tempoDeAtualizao = tempoDeAtualizao;
    }
}
