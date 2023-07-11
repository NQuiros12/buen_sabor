package vrs.backend.demo.controllers;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vrs.backend.demo.entities.*;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.PedidoServiceImpl;


@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping(path = "/pedidos")
@Transactional
public class PedidoController extends BaseControllerImpl<Pedido, PedidoServiceImpl> {

    private final PedidoServiceImpl pedidoServiceImpl;

    @Override
    @Transactional
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Pedido pedido) {
        try {
            pedidoServiceImpl.savePedido(pedido);
            return ResponseEntity.status(HttpStatus.OK).body(pedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el pedido" + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Override
    @Transactional
    public ResponseEntity<?> update(@RequestBody Pedido pedidoRecibido, @PathVariable("id") Long id) {

        try {
            pedidoServiceImpl.updatePedido(pedidoRecibido, id);
            return ResponseEntity.ok("Pedido actualizado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el pedido: " + e.getMessage());
        }
    }

}
