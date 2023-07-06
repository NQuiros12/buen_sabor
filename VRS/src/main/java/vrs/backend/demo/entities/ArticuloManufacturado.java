package vrs.backend.demo.entities;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
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


    private String tipoClase = "ArticuloManufacturado";
    @Column(name="tiempoEstimadoCocina")
    private int tiempoEstimadoCocina;
    @Column(name="esProductoFinal")
    private boolean productoFinal;
    @Column(name = "stockActual")
    private double stockActual;
    @Column(name = "stockMinimo")
    private double stockMinimo;
    @Column(name="denominacion")//Nombre
    private String denominacion;
    @Column(name = "descripcion")//Descripcion para el usuario
    private String descripcion;
    @Column(name = "receta")//Receta
    private String receta;
    @Column(name="precioVenta")
    private double precioVenta;
    @Column(name="precioCompra")
    private double precioCompra;
    @Column(name="imagen")
    private String imagen;
    @Column(name = "altaBaja")
    private boolean altaBaja;


    @OneToMany(mappedBy = "articuloManufacturado", cascade = CascadeType.ALL)
    private List<DetalleArticuloManufacturado> detalleArticuloManufacturados = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="fk_categoria")
    private CategoriaArticulo categoria;

    public List<DetalleArticuloManufacturado> getDetalleArticuloManufacturados() {
        return detalleArticuloManufacturados;
    }

}
