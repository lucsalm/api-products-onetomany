package com.treinamento.pedidoapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "pedido")
public class Pedido {
    @Id
    @SequenceGenerator(name = "pedido_idpedido_seq", sequenceName = "pedido_idpedido_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_idpedido_seq")
    @Column(name = "idpedido")
    private Integer id;

    @Column(name = "datadopedido")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @OneToMany(mappedBy = "pedido")
    @JsonIdentityReference
    private List<ItemPedido> itemPedidos;


    public Pedido(Integer id, Date date, List<ItemPedido> itemPedidos) {
        this.id = id;
        this.date = date;
        this.itemPedidos = itemPedidos;
    }


    public Pedido(Date date) {
        this.date = date;
    }

    public Pedido() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ItemPedido> getItemPedidos() {
        return itemPedidos;
    }

}
