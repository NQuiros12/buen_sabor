package vrs.backend.demo.controllers;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vrs.backend.demo.entities.*;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.PedidoServiceImpl;
import vrs.backend.demo.enums.EstadoPedido;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping(path = "/pedidos")
@Transactional
public class PedidoController extends BaseControllerImpl<Pedido, PedidoServiceImpl> {

    private final PedidoServiceImpl pedidoServiceImpl;
    @GetMapping("/buscar_pedido/{estadoPedido}")
    public List<Pedido> pedidoByEstado(@PathVariable EstadoPedido estadoPedido) throws Exception {
        return pedidoServiceImpl.buscarPedidosEstado(estadoPedido);
    }
    @GetMapping("/buscar_pedido_chef")
    public List<Pedido> pedidosChef(){
        return pedidoServiceImpl.buscarPedidosEstadoChef();
    }
    @GetMapping("/rejected_and_delivered/{page}")
    public ResponseEntity<?> pedidosRejectedDelivered(@PathVariable Integer page){
        try{
            Page<Pedido> pageResult = pedidoServiceImpl.PedidosByRechazadosEntregados(page);
            return ResponseEntity.status(HttpStatus.OK).body(pageResult);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al traer productos " + e);

        }
    }
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
