package vrs.backend.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vrs.backend.demo.entities.ArticuloManufacturado;
import vrs.backend.demo.entities.Producto;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.ArticuloManufacturadoServiceImpl;
import vrs.backend.demo.services.implementation.ProductoServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/articulos_manufacturado")
public class ArticuloManufacturadoController extends BaseControllerImpl<ArticuloManufacturado, ArticuloManufacturadoServiceImpl> {

    private ProductoServiceImpl productoServiceIpml;

    public ArticuloManufacturadoController(ProductoServiceImpl productoService) {
        this.productoServiceIpml = productoService;
    }

    @Override
    public ResponseEntity<?> save(ArticuloManufacturado entity) {

        try {
            super.save(entity);
            //Producto producto = new Producto(entity.getDenominacion(),entity.getDescripcion(),entity.getImagen() ,entity.getPrecioVenta(), entity.isAltaBaja());
            Producto producto = Producto.builder()
                    .producto(entity.getDenominacion())
                    .descripcion(entity.getDescripcion())
                    .imagen(entity.getImagen())
                    .precio_venta(entity.getPrecioVenta())
                    .altaBaja(entity.isAltaBaja())
                    .tipoClase("Producto")
                    .build();
            productoServiceIpml.save(producto);
            entity.setProducto(producto);
            super.update(entity, entity.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> update(ArticuloManufacturado entity, Long id) {

        try {
            super.update(entity, id);
            productoServiceIpml.update(entity.getProducto().getId(), Producto.builder()
                    .producto(entity.getDenominacion())
                    .descripcion(entity.getDescripcion())
                    .imagen(entity.getImagen())
                    .precio_venta(entity.getPrecioVenta())
                    .altaBaja(entity.isAltaBaja())
                    .tipoClase("Producto")
                    .build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

}
