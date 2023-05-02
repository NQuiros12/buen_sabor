package vrs.backend.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrs.backend.demo.generics.entities.Base;

import java.util.Date;

@Entity
@Table(name = "DetallePedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetallePedido extends Base {

    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "subtotal")
    private double subtotal;
    @ManyToOne
    @JoinColumn(name="fk_articulo_insumo")
    @MapsId
    private ArticuloInsumo articuloInsumo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_producto")
    private Producto producto;
    @ManyToOne
    @JoinColumn(name="fk_articulo_manufacturado")
    private ArticuloManufacturado articuloManufacturado;
    @ManyToOne
    @JoinColumn(name="fk_pedido")
    private Pedido pedido;

}
