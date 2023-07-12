package vrs.backend.demo.controllers;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vrs.backend.demo.entities.ArticuloManufacturado;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.ArticuloManufacturadoServiceImpl;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping(path = "/articulos_manufacturado")
public class ArticuloManufacturadoController extends BaseControllerImpl<ArticuloManufacturado, ArticuloManufacturadoServiceImpl> {

    private final ArticuloManufacturadoServiceImpl articuloManufacturadoServiceImpl;

    @GetMapping("/buscar_nombre/{nombreArtMan}")
    public List<ArticuloManufacturado> searchByName(@PathVariable String nombreArtMan) {
        return articuloManufacturadoServiceImpl.buscarPorNombre(nombreArtMan);
    }
    @GetMapping("/buscar_categoria/{nombreCategoria}")
    public List<ArticuloManufacturado> artByCategoria(@PathVariable String nombreCategoria) {
        return articuloManufacturadoServiceImpl.buscarPorNombre(nombreCategoria);
    }

    @Override
    @Transactional
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody ArticuloManufacturado articuloManufacturado) {
        try {
            articuloManufacturadoServiceImpl.saveArticuloManufacturado(articuloManufacturado);
            return ResponseEntity.status(HttpStatus.OK).body(articuloManufacturado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear producto "+ e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Override
    @Transactional
    public ResponseEntity<?> update(@RequestBody ArticuloManufacturado articuloRecibido, @PathVariable("id") Long id) {
        try {
            articuloManufacturadoServiceImpl.updateArticuloManufacturado(articuloRecibido, id);
            return ResponseEntity.ok("Producto actualizado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar producto " + e.getMessage());
        }
    }

}
