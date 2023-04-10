package vrs.backend.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Domicilio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Domicilio implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long domicilioID;
        @Column(name = "calle")
        private String calle;
        @Column(name = "localidad")
        private String localidad;
        @Column(name = "numero")
        private long numero;

        //Ver tema de departamentos, piso y numero

}
