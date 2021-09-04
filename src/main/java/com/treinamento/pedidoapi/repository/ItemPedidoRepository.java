package com.treinamento.pedidoapi.repository;

import com.treinamento.pedidoapi.model.ItemPedido;
import com.treinamento.pedidoapi.model.Pedido;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface ItemPedidoRepository extends CrudRepository<ItemPedido, Integer> {

    @Transactional
    Long deleteByPedido(Pedido pedido);
}
