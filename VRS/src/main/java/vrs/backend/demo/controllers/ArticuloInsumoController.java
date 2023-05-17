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
    private ArticuloInsumoServiceImpl articuloInsumoServiceImpl;

    public ArticuloInsumoController(ProductoServiceImpl productoService, ArticuloInsumoServiceImpl articuloInsumoServiceImpl) {
        this.productoServiceIpml = productoService;
        this.articuloInsumoServiceImpl = articuloInsumoServiceImpl;
    }

    @Override
    public ResponseEntity<?> save(ArticuloInsumo entity) {


        try {
            super.save(entity);
            if (!entity.isEsInsumo()) {
                Producto producto = Producto.builder()
                        .producto(entity.getDenominacion())
                        .descripcion(entity.getDescripcion())
                        .imagen(entity.getImagen())
                        .precio_venta(entity.getPrecioVenta())
                        .altaBaja(entity.isAltaBaja()).build();
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
            if (!entity.isEsInsumo()) { // Si ahora insumo es falso es un producto entonces lo creamos
                Producto producto = Producto.builder()
                        .producto(entity.getDenominacion())
                        .descripcion(entity.getDescripcion())
                        .imagen(entity.getImagen())
                        .precio_venta(entity.getPrecioVenta())
                        .altaBaja(entity.isAltaBaja())
                        .tipoClase("Producto")
                        .build();
                if (entity.getProducto().getId() != idNotProducto) {//si ya traia un id de un producto anterior actualizamos el producto
                    productoServiceIpml.update(entity.getProducto().getId(), producto);
                } else { //sino creamos el producto
                    productoServiceIpml.save(producto);
                    entity.setProducto(producto);
                }
            } else { //sino quiere decir que ahora es un insumo y no un producto por lo que asignamos al id 1 "No es Producto"
                if (entity.getProducto().getId() != idNotProducto) {
                    idProductoAnterior = entity.getProducto().getId();
                    Producto producto = productoServiceIpml.findById(idNotProducto);
                    entity.setProducto(producto);
                    entity.setImagen("");
                    entity.setDescripcion("");
                }
            }
            super.update(entity, id); //Guardamos la entidad ahora con su nuevo producto o producto actualizado
            if (idProductoAnterior != -1) { //borramos el producto que ya no tiene relaciones
                productoServiceIpml.delete(idProductoAnterior);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> delete(Long id) {

        try {
            long idProducto = articuloInsumoServiceImpl.findById(id).getProducto().getId();
            super.delete(id);
            if (idProducto != idNotProducto) productoServiceIpml.delete(idProducto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().build();
    }
}
