package vrs.backend.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrs.backend.demo.enums.Rol;
import vrs.backend.demo.generics.entities.Base;

@Entity
@Table(name = "Usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario extends Base {

    @Column(name = "idAuth0")
    private String idAuth0;

    @Column(name = "usuario")//correo del cliente
    private String usuario;
    @Column(name = "rol")
    private Rol rol;

}
