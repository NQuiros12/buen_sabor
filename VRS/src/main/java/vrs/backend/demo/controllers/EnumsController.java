package vrs.backend.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vrs.backend.demo.enums.EstadoPedido;
import vrs.backend.demo.enums.FormaPago;
import vrs.backend.demo.enums.Rol;
import vrs.backend.demo.enums.TipoEnvio;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/enums")
public class EnumsController {
    @GetMapping("/tipoEnvios")
    public ResponseEntity<TipoEnvio[]> getTipoEnvioValues() {
        TipoEnvio[] tipoEnvios = TipoEnvio.values();
        return ResponseEntity.ok(tipoEnvios);
    }

    @GetMapping("/tipoEnvios/{id}")
    public ResponseEntity<TipoEnvio> getTipoEnvioById(@PathVariable("id") String id) {
        try {
            TipoEnvio tipoEnvio = TipoEnvio.valueOf(id);
            return ResponseEntity.ok(tipoEnvio);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/formaPagos")
    public ResponseEntity<FormaPago[]> getFormaPagoValues() {
        FormaPago[] formaPagos = FormaPago.values();
        return ResponseEntity.ok(formaPagos);
    }

    @GetMapping("/formaPagos/{id}")
    public ResponseEntity<FormaPago> getFormaPagoById(@PathVariable("id") String id) {
        try {
            FormaPago formaPago = FormaPago.valueOf(id);
            return ResponseEntity.ok(formaPago);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/rols")
    public ResponseEntity<Rol[]> getRolValues() {
        Rol[] rols = Rol.values();
        return ResponseEntity.ok(rols);
    }
    @GetMapping("/rols/{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable("id") String id) {
        try {
            Rol rol = Rol.valueOf(id);
            return ResponseEntity.ok(rol);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }




    @GetMapping("/estadosPedido")
    public ResponseEntity<EstadoPedido[]> getEstadoPedidoValues() {
        EstadoPedido[] estadosPedido = EstadoPedido.values();
        return ResponseEntity.ok(estadosPedido);
    }
    @GetMapping("/estadosPedido/{id}")
    public ResponseEntity<EstadoPedido> getEstadoPedidoById(@PathVariable("id") String id) {
        try {
            EstadoPedido estadoPedido = EstadoPedido.valueOf(id);
            return ResponseEntity.ok(estadoPedido);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
