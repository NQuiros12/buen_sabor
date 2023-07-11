package vrs.backend.demo.services.implementation;

import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.MercadoPago;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.MercadoPagoRepository;
@Service
public class MercadoPagoServiceImpl extends BaseServiceImpl<MercadoPago,Long> {
    private final MercadoPagoRepository mercadoPagoRepository;

    public MercadoPagoServiceImpl(MercadoPagoRepository mercadoPagoPaymentRepository) {
        super(mercadoPagoPaymentRepository);
        this.mercadoPagoRepository = mercadoPagoPaymentRepository;
    }
}
