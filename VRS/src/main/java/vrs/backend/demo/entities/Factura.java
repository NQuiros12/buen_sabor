package vrs.backend.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrs.backend.demo.generics.entities.Base;

import java.util.Date;

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
    @Column(name = "formaPago")
    private String formaPago;
    @Column(name = "nroTarjeta")
    private String nroTarjeta;
    @Column(name = "totalVenta")
    private double totalVenta;
    @Column(name = "totalCosto")
    private double totalCosto;
/*
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "factura_detalleFactura",
            joinColumns = @JoinColumn(name = "facturaID"),
            inverseJoinColumns = @JoinColumn(name = "detalleFacturaID")
    )
    private List<DetalleFactura> detalleFacturas = new ArrayList<DetalleFactura>();

 */
}
