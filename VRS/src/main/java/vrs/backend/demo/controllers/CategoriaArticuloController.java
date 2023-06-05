package vrs.backend.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vrs.backend.demo.entities.ArticuloInsumo;
import vrs.backend.demo.entities.ArticuloManufacturado;
import vrs.backend.demo.entities.CategoriaArticulo;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.repositories.ArticuloManufacturadoRepository;
import vrs.backend.demo.repositories.CategoriaArticuloRepository;
import vrs.backend.demo.services.CategoriaArticuloService;
import vrs.backend.demo.services.implementation.ArticuloInsumoServiceImpl;
import vrs.backend.demo.services.implementation.CategoriaArticuloServiceImpl;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/categorias")
public class CategoriaArticuloController extends BaseControllerImpl<CategoriaArticulo, CategoriaArticuloServiceImpl> {
    @Autowired
    private CategoriaArticuloRepository categoriaArticuloRepository;
    @GetMapping("/buscar_nombre/{nombreArtMan}")
    public List<CategoriaArticulo> searchByName(@PathVariable String nombreCategoria) {
        return categoriaArticuloRepository.findByName(nombreCategoria);
    }
}
