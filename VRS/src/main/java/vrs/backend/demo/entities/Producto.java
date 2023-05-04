package vrs.backend.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrs.backend.demo.generics.entities.Base;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Producto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Producto extends Base {

    //Ver que poner aca
    @Column(name = "producto")
    private String producto;

    @Column(name = "precioVenta")
    private double precio_venta;
    @OneToMany(mappedBy = "producto")
    private List<DetalleFactura> detalleFacturas = new ArrayList<DetalleFactura>();
    @OneToMany(mappedBy = "producto")
    private List<DetallePedido> detallePedidos = new ArrayList<DetallePedido>();

    @OneToOne(mappedBy = "producto")
    private ArticuloInsumo articuloInsumo;

    @OneToOne(mappedBy = "producto")
    private ArticuloManufacturado articuloManufacturado;
}
