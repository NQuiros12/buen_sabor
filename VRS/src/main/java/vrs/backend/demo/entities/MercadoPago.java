package vrs.backend.demo.entities;
import com.mercadopago.*;

import com.mercadopago.MercadoPagoConfig;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vrs.backend.demo.generics.entities.Base;

@Entity
@Table(name="mercado_pago")
@Data //Getters and Setters
@NoArgsConstructor
@AllArgsConstructor
public class MercadoPago extends Base {

    private String paymentId;
    @OneToOne
    @JoinColumn(name = "fk_pedido")
    private Pedido id_pedido;

}
