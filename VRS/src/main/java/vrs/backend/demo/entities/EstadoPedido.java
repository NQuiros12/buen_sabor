package vrs.backend.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrs.backend.demo.generics.entities.Base;

@Entity
@Table(name = "EstadoPedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EstadoPedido extends Base {

    @Column(name = "EstadoPedido")
    private String estadoPedido;
}
