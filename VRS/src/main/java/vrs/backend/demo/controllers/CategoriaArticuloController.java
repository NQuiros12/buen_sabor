package vrs.backend.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vrs.backend.demo.entities.ArticuloInsumo;
import vrs.backend.demo.entities.CategoriaArticulo;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.CategoriaArticuloService;
import vrs.backend.demo.services.implementation.ArticuloInsumoServiceImpl;
import vrs.backend.demo.services.implementation.CategoriaArticuloServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/categorias")
public class CategoriaArticuloController extends BaseControllerImpl<CategoriaArticulo, CategoriaArticuloServiceImpl> {
}
