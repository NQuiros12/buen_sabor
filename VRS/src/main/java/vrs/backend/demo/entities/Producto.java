package vrs.backend.demo.entities;

import jakarta.persistence.*;
import lombok.*;
import vrs.backend.demo.generics.entities.Base;


@Entity
@Table(name = "Producto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Producto extends Base {


    @Column(name = "producto")
    private String producto;

    @Column(name = "precioVenta")
    private double precio_venta;

}
