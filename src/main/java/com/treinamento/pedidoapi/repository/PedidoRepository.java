package com.treinamento.pedidoapi.repository;

import com.treinamento.pedidoapi.model.Pedido;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository<Pedido,Integer> {
}
