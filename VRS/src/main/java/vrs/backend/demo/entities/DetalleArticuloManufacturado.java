package vrs.backend.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrs.backend.demo.generics.entities.Base;

@Entity
@Table(name = "DetalleArticuloManufacturado")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetalleArticuloManufacturado extends Base {
    @Column(name = "cantidad")
    private int cantidad;
    @ManyToOne
    @JoinColumn(name="id")
    @MapsId
    private UnidadMedida unidadMedida;
    @ManyToOne
    @JoinColumn(name="id")
    @MapsId
    private ArticuloManufacturado articuloManufacturado;
}
