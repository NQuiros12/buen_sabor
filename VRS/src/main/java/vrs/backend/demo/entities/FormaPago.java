package vrs.backend.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrs.backend.demo.generics.entities.Base;


@Entity
@Table(name = "FormaPago")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FormaPago extends Base {

    @Column(name = "formaPago")
    private String formaPago;
/*
    @OneToMany(mappedBy = "formaPago")
    private List<Factura> facturas = new ArrayList<Factura>();

 */
}
