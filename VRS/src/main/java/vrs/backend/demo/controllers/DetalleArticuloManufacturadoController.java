package vrs.backend.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vrs.backend.demo.entities.DetalleArticuloManufacturado;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.DetalleArticuloManufacturadoServiImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/detalles_articulos_manufacturados")
public class DetalleArticuloManufacturadoController extends BaseControllerImpl<DetalleArticuloManufacturado, DetalleArticuloManufacturadoServiImpl> {

}
