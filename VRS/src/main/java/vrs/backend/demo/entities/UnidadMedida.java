package vrs.backend.demo.entities;
import jakarta.persistence.OneToMany;
import vrs.backend.demo.generics.entities.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "UnidadMedida")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UnidadMedida extends Base {
    @Column(name = "tipo")
    private String tipo;
/*
    @OneToMany(mappedBy = "unidadMedida")
    private List<ArticuloInsumo> articuloInsumos = new ArrayList<ArticuloInsumo>();
    @OneToMany(mappedBy = "unidadMedida")
    private List<DetalleArticuloManufacturado> detalleArticuloManufacturados = new ArrayList<DetalleArticuloManufacturado>();

 */
}
