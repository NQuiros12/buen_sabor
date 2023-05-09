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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_articuloInsumo")
    private ArticuloInsumo articuloInsumo;

    @ManyToOne
    @JoinColumn(name="fk_unidad_medida")
    private UnidadMedida unidadMedida;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REMOVE })
    @JoinColumn(name="fk_articulo_manufacturado")
    private ArticuloManufacturado articuloManufacturado;
}
