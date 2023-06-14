package vrs.backend.demo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vrs.backend.demo.entities.Factura;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.DetalleFacturaServiceImpl;
import vrs.backend.demo.services.implementation.DetallePedidoServiceImpl;
import vrs.backend.demo.services.implementation.FacturaServiceImpl;
import vrs.backend.demo.services.implementation.PedidoServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping(path = "/factura")
public class FacturaController extends BaseControllerImpl<Factura, FacturaServiceImpl> {


    private final DetalleFacturaServiceImpl detalleFacturaServiceImpl;
    private final FacturaServiceImpl facturaServiceImpl;



}
