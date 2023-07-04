package vrs.backend.demo.services.implementation;

import vrs.backend.demo.entities.MercadoPago;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.MercadoPagoRepository;

public class MercadoPagoServiceImpl extends BaseServiceImpl<MercadoPago,Long> {
    private MercadoPagoRepository mercadoPagoRepository;

    public MercadoPagoServiceImpl(MercadoPagoRepository mercadoPagoPaymentRepository) {
        super(mercadoPagoPaymentRepository);
        this.mercadoPagoRepository = mercadoPagoPaymentRepository;
    }
}
