package vrs.backend.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
}
