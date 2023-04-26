package vrs.backend.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrs.backend.demo.generics.entities.Base;

@Entity
@Table(name = "Cliente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cliente extends Base {

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "telefono")
    private long telefono;
    @Column(name = "email")
    private String email;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_domicilio")
    private Domicilio domicilio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;
}
