package com.treinamento.pedidoapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;

@Entity(name = "itempedido")
public class ItemPedido {
    @Id
    @SequenceGenerator(name = "itempedido_iditempedido_seq", sequenceName = "itempedido_iditempedido_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itempedido_iditempedido_seq")
    @Column(name = "iditempedido")
    private Integer id;

    @Column(name = "nome")
    private String name;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "valor")
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "idpedido")
    @JsonIdentityReference
    private Pedido pedido;

    public ItemPedido(Integer id, String name, Integer quantidade, Double valor) {
        this.id = id;
        this.name = name;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public ItemPedido(String name, Integer quantidade, Double valor) {
        this.name = name;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public ItemPedido(String name, Integer quantidade, Double valor, Pedido pedido) {
        this.name = name;
        this.quantidade = quantidade;
        this.valor = valor;
        this.pedido = pedido;
    }

    public ItemPedido() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
