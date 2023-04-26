package vrs.backend.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrs.backend.demo.generics.entities.Base;

@Entity
@Table(name = "Domicilio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Domicilio extends Base {

        @Column(name = "calle")
        private String calle;
        @Column(name = "localidad")
        private String localidad;
        @Column(name = "numero")
        private long numero;

        //Ver tema de departamentos, piso y numero

}
