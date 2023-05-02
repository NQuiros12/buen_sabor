package vrs.backend.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrs.backend.demo.generics.entities.Base;

@Entity
@Table(name = "DetalleFactura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetalleFactura extends Base {

    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "subtotal")
    private double subtotal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_factura")
    private Factura factura;

    @ManyToOne
    @JoinColumn(name="fk_articulo_insumo")
    private ArticuloInsumo articuloInsumo;

    @ManyToOne
    @JoinColumn(name="fk_articulo_manufacturado")
    private ArticuloManufacturado articuloManufacturado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_producto")
    private Producto producto;
}
