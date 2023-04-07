package vrs.backend.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Factura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long facturaID;
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

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleFactura> detallesFactura = new ArrayList<>();
}
