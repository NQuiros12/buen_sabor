package vrs.backend.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrs.backend.demo.generics.entities.Base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Pedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pedido extends Base {

    private String tipoClase = "Pedido";
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "numero")
    private int numero;

    @ManyToOne
    @JoinColumn(name = "fk_domicilio")
    private Domicilio domicilio;

    @ManyToOne
    @JoinColumn(name = "fk_estadoPedido")
    private EstadoPedido estadoPedido;

    @ManyToOne
    @JoinColumn(name = "fk_tipoEnvio")
    private TipoEnvio tipoEnvio;

    @ManyToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.REMOVE)
    private List<DetallePedido> detallePedidos = new ArrayList<>();

}

