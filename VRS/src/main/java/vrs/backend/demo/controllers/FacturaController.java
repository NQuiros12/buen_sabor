package vrs.backend.demo.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vrs.backend.demo.entities.Factura;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.FacturaServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping(path = "/factura")
public class FacturaController extends BaseControllerImpl<Factura, FacturaServiceImpl> {

}
