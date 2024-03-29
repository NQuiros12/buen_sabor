package vrs.backend.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrs.backend.demo.enums.EstadoPedido;
import vrs.backend.demo.enums.FormaPago;
import vrs.backend.demo.enums.TipoEnvio;
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

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "fecha")
    private Date fecha;

    //Ver
    @Column(name = "numero")
    private int numero;

    @Column(name = "monto")
    private double monto;

    @Column(name = "pagoConfirmado")
    private boolean pagoConfirmado;

    @ManyToOne
    @JoinColumn(name = "fk_domicilio")
    private Domicilio domicilio;

    @JoinColumn(name = "fk_estadoPedido")
    private EstadoPedido estadoPedido;


    @JoinColumn(name = "fk_tipoEnvio")
    private TipoEnvio tipoEnvio;

    @JoinColumn(name = "fk_formaPago")
    private FormaPago formaPago;

    @ManyToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuarioEntrega;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    public List<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }
}

