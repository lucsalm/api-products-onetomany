package com.treinamento.pedidoapi.controller;

import com.treinamento.pedidoapi.model.ItemPedido;
import com.treinamento.pedidoapi.model.Pedido;
import com.treinamento.pedidoapi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    PedidoService service;

    @GetMapping
    public ResponseEntity<List<Pedido>> pedidos() {

        if (service.pedidos().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.pedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findPedidoById(@PathVariable Integer id) {
        if (!service.hasPedido(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.getPedidoById(id));
    }

    @GetMapping("/{id}/itempedidos")
    public ResponseEntity<List<ItemPedido>> itensPedidos(@PathVariable Integer id) {
        if (!service.hasPedido(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(service.getPedidoById(id).getItemPedidos());
    }

    @PostMapping("/add")
    public ResponseEntity<Void> insert(@RequestBody Pedido pedido) {
        if (service.hasNulls(pedido)) {
            return ResponseEntity.status(403).build();
        }
        service.insert(pedido);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/adds")
    public ResponseEntity<Void> inserts(@RequestBody List<Pedido> pedidos) {
        for (Pedido pedido : pedidos) {
            if (service.hasNulls(pedido)) {
                return ResponseEntity.status(403).build();
            }
        }
        for (Pedido pedido : pedidos) {
            service.insert(pedido);
        }
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Pedido pedido, @PathVariable Integer id) {
        if (!service.hasPedido(id)) {
            return ResponseEntity.notFound().build();
        }
        service.update(id, pedido);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!service.hasPedido(id)) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id")
    public ResponseEntity<List<Pedido>> queryStringById(@RequestParam List<Integer> ids) {
        List<Pedido> pedidos = new ArrayList<>();
        for (Integer i : ids) {
            if (!service.hasPedido(i)) {
                return ResponseEntity.status(403).build();
            }
            pedidos.add(service.getPedidoById(i));
        }
        return ResponseEntity.ok(pedidos);
    }

}
