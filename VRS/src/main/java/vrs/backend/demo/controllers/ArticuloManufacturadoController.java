package vrs.backend.demo.controllers;


import jakarta.transaction.Transactional;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vrs.backend.demo.entities.ArticuloManufacturado;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.ArticuloManufacturadoServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/articulos_manufacturado")
public class ArticuloManufacturadoController extends BaseControllerImpl<ArticuloManufacturado, ArticuloManufacturadoServiceImpl> {

    private final ArticuloManufacturadoServiceImpl articuloManufacturadoServiceImpl;
    public ArticuloManufacturadoController(ArticuloManufacturadoServiceImpl articuloManufacturadoServiceImpl) {
        this.articuloManufacturadoServiceImpl = articuloManufacturadoServiceImpl;
    }

    @GetMapping("/allByName/{page}/{orderPrice}/{nombreArtMan}")
    public ResponseEntity<?>  searchByName(@PathVariable Integer page,@PathVariable String nombreArtMan,@PathVariable String orderPrice) {
        try {
            Page<ArticuloManufacturado> pageResult = articuloManufacturadoServiceImpl.buscarPorNombre(nombreArtMan, page, orderPrice);
            return ResponseEntity.status(HttpStatus.OK).body(pageResult);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al traer productos " + e);
        }
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

    @GetMapping("/pagedPrice/{page}/{orderPrice}/{category}")
    public ResponseEntity<?> getAllOrderPrice(@PathVariable Integer page, @PathVariable String orderPrice, @PathVariable String category) {
        try {
            Page<ArticuloManufacturado> pageResult = articuloManufacturadoServiceImpl.orderCategoryByPrice(page, orderPrice, category);
            return ResponseEntity.status(HttpStatus.OK).body(pageResult);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al traer productos " + e);
        }
    }





}
