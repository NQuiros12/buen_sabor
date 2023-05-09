package vrs.backend.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vrs.backend.demo.entities.DetalleFactura;
import vrs.backend.demo.entities.DetallePedido;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.DetalleFacturaServiceImpl;
import vrs.backend.demo.services.implementation.DetallePedidoServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/detallefacturas")
public class DetalleFacturaController extends BaseControllerImpl<DetalleFactura, DetalleFacturaServiceImpl> {
}
