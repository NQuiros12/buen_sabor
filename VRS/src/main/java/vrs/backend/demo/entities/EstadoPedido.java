package vrs.backend.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrs.backend.demo.generics.entities.Base;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EstadoPedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EstadoPedido extends Base {

    @Column(name = "EstadoPedido")
    private String estadoPedido;

    @OneToMany(mappedBy = "estadoPedido")
    private List<Pedido> pedidos = new ArrayList<Pedido>();
}
