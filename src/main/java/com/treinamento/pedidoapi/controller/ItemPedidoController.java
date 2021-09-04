package com.treinamento.pedidoapi.controller;

import com.treinamento.pedidoapi.model.ItemPedido;
import com.treinamento.pedidoapi.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/itempedido")
public class ItemPedidoController {
    @Autowired
    ItemPedidoService service;

    @GetMapping
    public ResponseEntity<List<ItemPedido>> itemPedidos() {
        if (service.itemPedidos().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.itemPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> getItemPedido(@PathVariable Integer id) {
        if (service.hasItemPedido(id)) {
            return ResponseEntity.ok(service.getItemPedido(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Void> insert(@RequestBody ItemPedido itemPedido) {
        if (service.hasNulls(itemPedido)) {
            return ResponseEntity.status(403).build();
        }
        service.insert(itemPedido);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/adds")
    public ResponseEntity<Void> inserts(@RequestBody List<ItemPedido> itemPedidos) {
        for (ItemPedido itemPedido : itemPedidos) {
            if (service.hasNulls(itemPedido)) {
                return ResponseEntity.status(403).build();
            }
        }
        for (ItemPedido itemPedido : itemPedidos) {
            service.insert(itemPedido);
        }
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody ItemPedido itemPedido, @PathVariable Integer id) {
        if (service.hasItemPedido(id)) {
            service.update(itemPedido, id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (service.hasItemPedido(id)) {
            service.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/id")
    public ResponseEntity<List<ItemPedido>> queryStringById(@RequestParam List<Integer> ids) {
        List<ItemPedido> itemPedidos = new ArrayList<>();
        for (Integer i : ids) {
            if (!service.hasItemPedido(i)) {
                return ResponseEntity.notFound().build();
            }
            itemPedidos.add(service.getItemPedido(i));
        }
        return ResponseEntity.ok(itemPedidos);
    }


}
