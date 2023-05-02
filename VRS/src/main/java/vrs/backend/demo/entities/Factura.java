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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_formaPago")
    private FormaPago formaPago;
    //Ver si es una relacion One to one o one to Many

    @Column(name = "nroTarjeta")
    private String nroTarjeta;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_pedido")
    private Pedido pedido;
    @OneToMany(mappedBy = "factura")
    private List<DetalleFactura> detalleFacturas = new ArrayList<DetalleFactura>();

}
