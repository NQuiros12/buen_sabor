package vrs.backend.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vrs.backend.demo.entities.EstadoPedido;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.EstadoPedidoServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/estadopedidos")
public class EstadoPedidoController extends BaseControllerImpl<EstadoPedido, EstadoPedidoServiceImpl> {

}
