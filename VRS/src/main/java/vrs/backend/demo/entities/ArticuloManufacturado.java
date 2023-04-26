package vrs.backend.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrs.backend.demo.generics.entities.Base;

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
}
