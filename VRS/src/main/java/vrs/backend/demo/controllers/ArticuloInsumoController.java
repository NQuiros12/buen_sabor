package vrs.backend.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vrs.backend.demo.entities.ArticuloInsumo;

import vrs.backend.demo.entities.Producto;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.ArticuloInsumoServiceImpl;
import vrs.backend.demo.services.implementation.ProductoServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/articulosinsumos")
public class ArticuloInsumoController extends BaseControllerImpl<ArticuloInsumo, ArticuloInsumoServiceImpl> {


    long idNotProducto = 1L;
    private ProductoServiceImpl productoServiceIpml;

    public ArticuloInsumoController(ProductoServiceImpl productoService) {
        this.productoServiceIpml = productoService;
    }

    @Override
    public ResponseEntity<?> save(ArticuloInsumo entity) {
        super.save(entity);

        try {
            if (!entity.isEsInsumo()) {
                Producto producto = new Producto(entity.getDenominacion(), entity.getPrecioVenta());

                productoServiceIpml.save(producto);
                entity.setProducto(producto);
                super.update(entity, entity.getId());
            } else {
                entity.setProducto(productoServiceIpml.findById(idNotProducto));
                super.update(entity, entity.getId());
                return ResponseEntity.ok().build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<?> update(ArticuloInsumo entity, Long id) {
        try {

            long idProductoAnterior = -1;

            if (!entity.isEsInsumo()) { // si es falso creamos producto
                if (entity.getProducto().getId() != idNotProducto) {
                    Producto producto = new Producto(entity.getDenominacion(), entity.getPrecioVenta());
                    productoServiceIpml.update(entity.getProducto().getId(), producto);
                } else {
                    Producto producto = new Producto(entity.getDenominacion(), entity.getPrecioVenta());
                    productoServiceIpml.save(producto);
                    entity.setProducto(producto);
                }
            } else {
                if (entity.getProducto().getId() != idNotProducto) {
                    idProductoAnterior = entity.getProducto().getId();
                    Producto nuevoProducto = productoServiceIpml.findById(idNotProducto);
                    entity.setProducto(nuevoProducto);
                }
            }
            super.update(entity, id);
            if (idProductoAnterior != -1) {
                productoServiceIpml.delete(idProductoAnterior);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

}
