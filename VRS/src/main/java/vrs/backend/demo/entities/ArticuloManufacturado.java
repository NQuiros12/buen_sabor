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
@Table(name="ArticuloManufacturado")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticuloManufacturado extends Base {


    @Column(name="tiempoEstimadoCocina")
    private int tiempoEstimadoCocina;
    @Column(name="denominacion")
    private String denominacion;
    @Column(name="precioVenta")
    private double precioVenta;
    @Column(name="imagen")
    private String imagen;
    @OneToMany(mappedBy = "articuloManufacturado")
    private List<DetalleArticuloManufacturado> detalleArticuloManufacturados = new ArrayList<DetalleArticuloManufacturado>();
    @OneToMany(mappedBy = "articuloManufacturado")
    private List<DetalleFactura> detalleFactura = new ArrayList<DetalleFactura>();
    @OneToMany(mappedBy = "articuloManufacturado")
    private List<DetallePedido> detallePedidos = new ArrayList<DetallePedido>();
    @OneToOne
    @JoinColumn(name = "fk_producto")
    @MapsId
    private Producto producto;
}