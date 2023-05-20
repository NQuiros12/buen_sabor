package vrs.backend.demo.controllers;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vrs.backend.demo.entities.ArticuloManufacturado;
import vrs.backend.demo.entities.DetalleArticuloManufacturado;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.ArticuloManufacturadoServiceImpl;
import vrs.backend.demo.services.implementation.DetalleArticuloManufacturadoServiImpl;

import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping(path = "/articulos_manufacturado")
public class ArticuloManufacturadoController extends BaseControllerImpl<ArticuloManufacturado, ArticuloManufacturadoServiceImpl> {


    private final DetalleArticuloManufacturadoServiImpl detalleArticuloManufacturadoServiImpl;
    private final ArticuloManufacturadoServiceImpl articuloManufacturadoServiceImpl;


    @Override
    @Transactional
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody ArticuloManufacturado articuloManufacturado) {
        try {
            // Guardar el artículo manufacturado principal
            super.save(articuloManufacturado);

            // Asignar el ID del artículo manufacturado a los detalles
            if (!articuloManufacturado.isProductoFinal()) {
                for (DetalleArticuloManufacturado detalle : articuloManufacturado.getDetalleArticuloManufacturados()) {
                    detalle.setArticuloManufacturado(articuloManufacturado);
                }

                // Guardar los detalles
                detalleArticuloManufacturadoServiImpl.saveAll(articuloManufacturado.getDetalleArticuloManufacturados());
            }

            return ResponseEntity.status(HttpStatus.OK).body(articuloManufacturado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }


    //UPDATE NO FUNCIONA
    @Transactional
    @PutMapping("/prueba/{id}")
    public ResponseEntity<?> update2(ArticuloManufacturado articuloManufacturado, Long id) {
        try {
            // Verificar si es un producto final y no realizar operaciones relacionadas con los detalles
            ArticuloManufacturado previoArticulo = articuloManufacturadoServiceImpl.findById(id);

            //Si tiene detalles previos los borro todos
            if (previoArticulo != null && previoArticulo.getDetalleArticuloManufacturados() != null) {
                Iterator<DetalleArticuloManufacturado> iterator = previoArticulo.getDetalleArticuloManufacturados().iterator();
                while (iterator.hasNext()) {
                    detalleArticuloManufacturadoServiImpl.delete(iterator.next().getId());
                }
            }

            //si es un producto final borro las referencias y no le creod etalles, sino creo nuevos detalles
            if (articuloManufacturado.isProductoFinal()) {
                articuloManufacturado.setDetalleArticuloManufacturados(null); // Eliminar referencias a los detalles
            } else {
                articuloManufacturado.setDetalleArticuloManufacturados(null);
                List<DetalleArticuloManufacturado> existingDetalles = articuloManufacturado.getDetalleArticuloManufacturados();
                for (DetalleArticuloManufacturado detalle : existingDetalles) {
                    detalle.setArticuloManufacturado(articuloManufacturado);
                }
                // Guardar los detalles
                detalleArticuloManufacturadoServiImpl.saveAll(existingDetalles);
            }

            super.update(articuloManufacturado, id);

            return ResponseEntity.status(HttpStatus.OK).body(articuloManufacturado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }






}
