package vrs.backend.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vrs.backend.demo.entities.Factura;
import vrs.backend.demo.genericos.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.FacturaServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/factura")
public class FacturaController extends BaseControllerImpl<Factura, FacturaServiceImpl> {
}
