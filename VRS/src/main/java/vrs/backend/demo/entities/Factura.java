package vrs.backend.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrs.backend.demo.generics.entities.Base;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Factura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Factura extends Base {


    private String tipoClase = "Factura";
    //Ver
    @Column(name = "numero")
    private int numero;

    @OneToOne
    @JoinColumn(name = "fk_pedido")
    private Pedido pedido;


    @OneToMany(mappedBy = "factura",  cascade = CascadeType.ALL)
    private List<DetalleFactura> detalleFacturas = new ArrayList<>();

    public List<DetalleFactura> getDetalleFacturas() {
        return detalleFacturas;
    }


}
