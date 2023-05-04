package vrs.backend.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vrs.backend.demo.entities.ArticuloManufacturado;
import vrs.backend.demo.entities.Factura;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.ArticuloManufacturadoServiceImpl;
import vrs.backend.demo.services.implementation.FacturaServiceImpl;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/articulos_manufacturado")
public class ArticuloManufacturadoController extends BaseControllerImpl<ArticuloManufacturado, ArticuloManufacturadoServiceImpl> {
}
