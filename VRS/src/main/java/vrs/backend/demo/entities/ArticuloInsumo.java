package vrs.backend.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrs.backend.demo.generics.entities.Base;


@Entity
@Table(name="ArticuloInsumo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticuloInsumo extends Base {

    private String tipoClase = "ArticuloInsumo";

    @Column(name = "denominacion")
    private String denominacion;
    @Column(name = "descripcion")
    private String descripcion;
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
    @Column(name = "imagen")
    private String imagen;
    @Column(name = "altaBaja")
    private boolean altaBaja;

    @ManyToOne
    @JoinColumn(name="fk_categoria")
    private CategoriaArticulo categoria;

    @ManyToOne
    @JoinColumn(name="fk_unidad_medida")
    private UnidadMedida unidadMedida;

    @OneToOne
    @JoinColumn(name = "fk_producto")
    private Producto producto;



}
