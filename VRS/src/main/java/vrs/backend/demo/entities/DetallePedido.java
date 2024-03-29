package vrs.backend.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrs.backend.demo.generics.entities.Base;


@Entity
@Table(name = "DetallePedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetallePedido extends Base {

    private String tipoClase = "DetallePedido";

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "subtotal")
    private double subtotal;

    @JsonIgnore //cambio 14/06
    @ManyToOne
    @JoinColumn(name="fk_pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "fk_articuloManufacturado")
    private ArticuloManufacturado articuloManufacturado;
}
