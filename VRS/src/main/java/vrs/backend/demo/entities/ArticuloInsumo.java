package vrs.backend.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.Join;
import vrs.backend.demo.generics.entities.Base;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="ArticuloInsumo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticuloInsumo extends Base {

    @Column(name = "denominacion")
    private String denominacion;
    @Column(name = "esInsumo")
    private boolean esInsumo;
    @Column(name = "precioCompra")
    private double precioCompra;
    @Column(name = "precioVenta")
    private double precioVenta;
    @Column(name = "stockActual")
    private double stockActual;
    @Column(name = "stockMinimo")
    private double stockMinimo;

    @ManyToOne
    @JoinColumn(name="fk_categoria")
    private CategoriaArticulo categoria;

    @OneToMany(mappedBy = "articuloInsumo")
    private List<DetallePedido>  detallesPedido = new ArrayList<DetallePedido>();
    @OneToMany(mappedBy = "articuloInsumo")
    private List<DetalleFactura>  detallesFactura = new ArrayList<DetalleFactura>();
    @ManyToOne
    @JoinColumn(name="fk_unidad_medida")
    private UnidadMedida unidadMedida;

    @OneToOne
    @JoinColumn(name = "fk_producto")
    @MapsId
    private Producto producto;



}
