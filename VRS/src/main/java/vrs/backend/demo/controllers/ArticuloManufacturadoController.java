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

import java.util.ArrayList;
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
            if (articuloManufacturado.isProductoFinal())  articuloManufacturado.setDetalleArticuloManufacturados(null);
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


    @PutMapping("/{id}")
    @Override
    @Transactional
    public ResponseEntity<?> update(@RequestBody ArticuloManufacturado articuloRecibido, @PathVariable("id") Long id) {

        try {

            // Obtener el objeto ArticuloManufacturado existente
            ArticuloManufacturado articuloPrevio = articuloManufacturadoServiceImpl.findById(id);

            if (articuloPrevio == null) {
                return ResponseEntity.notFound().build();
            }
            // Actualizamos el articulo per
            actualizarArticuloPrevio(articuloPrevio, articuloRecibido);

            if (articuloRecibido.isProductoFinal()) {
                eliminarDetallesArticulo(articuloPrevio);
            } else {
                actualizarDetallesArticulo(articuloPrevio, articuloRecibido);
            }

            // Guardar los cambios en la base de datos
            articuloManufacturadoServiceImpl.update(id, articuloPrevio);

            return ResponseEntity.ok("Articulo actualizado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el articulo." + e);
        }
    }

    //Metodo que actualiza todos los atributos del Articulo menos sus detalles
    private void actualizarArticuloPrevio(ArticuloManufacturado articuloPrevio, ArticuloManufacturado articuloRecibido) {
        articuloPrevio.setTipoClase(articuloRecibido.getTipoClase());
        articuloPrevio.setTiempoEstimadoCocina(articuloRecibido.getTiempoEstimadoCocina());
        articuloPrevio.setProductoFinal(articuloRecibido.isProductoFinal());
        articuloPrevio.setDenominacion(articuloRecibido.getDenominacion());
        articuloPrevio.setDescripcion(articuloRecibido.getDescripcion());
        articuloPrevio.setReceta(articuloRecibido.getReceta());
        articuloPrevio.setPrecioVenta(articuloRecibido.getPrecioVenta());
        articuloPrevio.setImagen(articuloRecibido.getImagen());
        articuloPrevio.setAltaBaja(articuloRecibido.isAltaBaja());
        articuloPrevio.setCategoria(articuloRecibido.getCategoria());
    }

    //Metodo para eliminar sus detalles tanto del articulo como sus persistencias
    private void eliminarDetallesArticulo(ArticuloManufacturado articuloPrevio) throws Exception {
        Iterator<DetalleArticuloManufacturado> iterator = articuloPrevio.getDetalleArticuloManufacturados().iterator();
        while (iterator.hasNext()) {
            DetalleArticuloManufacturado detallePrevio = iterator.next();
            detalleArticuloManufacturadoServiImpl.delete(detallePrevio.getId());
            iterator.remove();
        }
        articuloPrevio.setDetalleArticuloManufacturados(null);
    }

    //Metodo que crea, actualiza o borra detalles en funcion a lo recibido en el JSON
    private void actualizarDetallesArticulo(ArticuloManufacturado articuloPrevio, ArticuloManufacturado articuloRecibido) throws Exception {
        List<DetalleArticuloManufacturado> detalles = new ArrayList<>();
        Iterator<DetalleArticuloManufacturado> detallesRecibidos = articuloRecibido.getDetalleArticuloManufacturados().iterator();
        while (detallesRecibidos.hasNext()) {
            DetalleArticuloManufacturado detalleRecibido = detallesRecibidos.next();
            DetalleArticuloManufacturado detalleActualizado;

            // Si el detalle ya existe, se actualiza
            if (detalleRecibido.getId() != null) {
                detalleActualizado = detalleArticuloManufacturadoServiImpl.findById(detalleRecibido.getId());
                if (detalleActualizado == null) {
                    throw new IllegalArgumentException("El detalle no existe.");

                }

                // Actualizar los datos del detalle
                detalleActualizado.setCantidad(detalleRecibido.getCantidad());
                detalleActualizado.setArticuloInsumo(detalleRecibido.getArticuloInsumo());
            } else {
                // Si el detalle es nuevo, se crea
                detalleActualizado = new DetalleArticuloManufacturado();
                detalleActualizado.setCantidad(detalleRecibido.getCantidad());
                detalleActualizado.setArticuloManufacturado(articuloPrevio);
                detalleActualizado.setArticuloInsumo(detalleRecibido.getArticuloInsumo());
            }

            detalles.add(detalleActualizado);
        }

        // Eliminar los detalles que existían pero no se recibieron en el JSON
        List<DetalleArticuloManufacturado> detallesActuales = articuloPrevio.getDetalleArticuloManufacturados();
        Iterator<DetalleArticuloManufacturado> actualIterator = detallesActuales.iterator();
        while (actualIterator.hasNext()) {
            DetalleArticuloManufacturado detalleActual = actualIterator.next();
            if (!detalles.contains(detalleActual)) {
                detalleArticuloManufacturadoServiImpl.delete(detalleActual.getId());
                actualIterator.remove();
            }
        }

        articuloPrevio.setDetalleArticuloManufacturados(detalles);
    }




}
