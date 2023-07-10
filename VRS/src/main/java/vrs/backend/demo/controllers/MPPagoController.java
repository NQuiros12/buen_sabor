package vrs.backend.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vrs.backend.demo.entities.MercadoPago;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.MercadoPagoServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "mp_pay")
public class MPPagoController extends BaseControllerImpl<MercadoPago, MercadoPagoServiceImpl> {
    public MPPagoController(MercadoPagoServiceImpl service){
        //super(service);
    }
}
