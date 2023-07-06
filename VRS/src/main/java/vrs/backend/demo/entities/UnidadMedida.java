package vrs.backend.demo.entities;
import vrs.backend.demo.generics.entities.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "UnidadMedida")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UnidadMedida extends Base {

    private String tipoClase = "UnidadMedida";
    @Column(name = "denominacion")
    private String denominacion;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "altaBaja")
    private boolean altaBaja;

}
