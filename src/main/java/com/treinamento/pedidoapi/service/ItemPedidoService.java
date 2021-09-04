package com.treinamento.pedidoapi.service;

import com.treinamento.pedidoapi.model.ItemPedido;
import com.treinamento.pedidoapi.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {
    @Autowired
    ItemPedidoRepository repository;

    public List<ItemPedido> itemPedidos() {
        return (List<ItemPedido>) repository.findAll();
    }


    public void insert(ItemPedido itemPedido) {
        if (!hasNulls(itemPedido)) {
            repository.save(itemPedido);
        }
    }

    public void update(ItemPedido itemPedido, Integer id) {
        ItemPedido itemPedido1 = getItemPedido(id);
        if (itemPedido.getName() != null) {
            itemPedido1.setName(itemPedido.getName());
        }
        if (itemPedido.getQuantidade() != null) {
            itemPedido1.setQuantidade(itemPedido.getQuantidade());
        }
        if (itemPedido.getValor() != null) {
            itemPedido1.setValor(itemPedido.getValor());
        }
        repository.save(itemPedido1);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public boolean hasNulls(ItemPedido itemPedido) {
        if (itemPedido.getName() == null || itemPedido.getQuantidade() == null || itemPedido.getValor() == null) {
            return true;
        }
        return false;
    }

    public boolean hasItemPedido(Integer id) {
        return repository.existsById(id);
    }

    public ItemPedido getItemPedido(Integer id) {
        return repository.findById(id).get();
    }

}
