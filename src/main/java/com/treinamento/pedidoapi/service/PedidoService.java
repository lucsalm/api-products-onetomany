package com.treinamento.pedidoapi.service;

import com.treinamento.pedidoapi.model.Pedido;
import com.treinamento.pedidoapi.repository.ItemPedidoRepository;
import com.treinamento.pedidoapi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository repository;
    @Autowired
    ItemPedidoRepository repository2;


    public List<Pedido> pedidos() {
        return (List<Pedido>) repository.findAll();
    }

    public void insert(Pedido pedido) {
        repository.save(pedido);
    }

    public void update(Integer id, Pedido pedido) {
        Pedido pedido1 = getPedidoById(id);
        pedido1.setDate(pedido.getDate());
        repository.save(pedido1);
    }

    public void delete(Integer id) {
        repository2.deleteByPedido(getPedidoById(id));
        repository.deleteById(id);
    }

    public boolean hasNulls(Pedido pedido) {
        if (pedido.getDate() == null) {
            return true;
        }
        return false;
    }

    public Pedido getPedidoById(Integer id) {
        return repository.findById(id).get();
    }

    public boolean hasPedido(Integer id) {
        return repository.existsById(id);
    }

}
