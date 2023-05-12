package vrs.backend.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "Factura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Factura extends Base {


    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "numero")
    private int numero;
    @Column(name = "montoDescuento")
    private double montoDescuento;

    @ManyToOne
    @JoinColumn(name = "fk_formaPago")
    private FormaPago formaPago;

    @Column(name = "nroTarjeta")
    private String nroTarjeta;

    @OneToOne
    @JoinColumn(name = "fk_pedido")
    private Pedido pedido;

    @JsonIgnore
    @OneToMany(mappedBy = "factura", cascade = CascadeType.REMOVE)
    private List<DetalleFactura> detalleFacturas = new ArrayList<>();


}
