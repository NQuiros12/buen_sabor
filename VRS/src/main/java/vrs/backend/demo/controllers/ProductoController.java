package vrs.backend.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vrs.backend.demo.entities.ArticuloInsumo;
import vrs.backend.demo.entities.ArticuloManufacturado;
import vrs.backend.demo.entities.Producto;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.ArticuloInsumoServiceImpl;
import vrs.backend.demo.services.implementation.ArticuloManufacturadoServiceImpl;
import vrs.backend.demo.services.implementation.ProductoServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/productos")
public class ProductoController extends BaseControllerImpl<Producto, ProductoServiceImpl> {

    private ArticuloInsumoServiceImpl articuloInsumoServiceImpl;
    private ArticuloManufacturadoServiceImpl articuloManufacturadoServiceImpl;

    public ProductoController(ArticuloInsumoServiceImpl articuloInsumoServiceImpl, ArticuloManufacturadoServiceImpl articuloManufacturadoServiceImpl) {
        this.articuloInsumoServiceImpl = articuloInsumoServiceImpl;
        this.articuloManufacturadoServiceImpl = articuloManufacturadoServiceImpl;
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            List<ArticuloManufacturado> articulosManufacturados = articuloManufacturadoServiceImpl.findAll();

            // Buscamos el articulo con el producto con el mismo ID

            Optional<ArticuloManufacturado> articuloManufacturado = articulosManufacturados.stream()
                    .filter(am -> am.getProducto().getId().equals(id))
                    .findFirst();

            if (articuloManufacturado.isPresent())
                return ResponseEntity.ok(articuloManufacturado.get());// Si lo encontramos lo devolvemos

            List<ArticuloInsumo> articuloInsumos = articuloInsumoServiceImpl.findAll();

            Optional<ArticuloInsumo> articuloInsumo = articuloInsumos.stream()
                    .filter(ai -> ai.getProducto().getId().equals(id))
                    .findFirst();
            if (articuloInsumo.isPresent()) return ResponseEntity.ok(articuloInsumo.get());

            // Si no lo encontramos no existe
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
