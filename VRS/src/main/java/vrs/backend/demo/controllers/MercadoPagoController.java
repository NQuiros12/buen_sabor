package vrs.backend.demo.controllers;

import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vrs.backend.demo.entities.MercadoPagoItem.ItemMercadoPago;
import vrs.backend.demo.services.MercadoPagoService;

import vrs.backend.demo.services.implementation.PedidoServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "mercadopago")
public class MercadoPagoController {
    private PedidoServiceImpl pedidoService;
    public String urlSuccess = "http://localhost:9000/mercadopago/success";
    public String urlFailure = "http://localhost:9000/mercadopago/failure";
    public MercadoPagoController(PedidoServiceImpl pedidoService){
        this.pedidoService = pedidoService;

    }

    @PostMapping("/createRedirect")
    public ResponseEntity<?> createRedirect(@RequestBody ItemMercadoPago itemMercadoPago) throws MPException, MPApiException,InterruptedException{
        List<PreferenceItemRequest> items = new ArrayList<>();

        PreferenceItemRequest item =
                PreferenceItemRequest.builder()
                        .id(itemMercadoPago.getCode())
                        .title(itemMercadoPago.getTitle())
                        .description(itemMercadoPago.getDescription())
                        .quantity(1)
                        .currencyId("ARS")
                        .unitPrice(new BigDecimal(itemMercadoPago.getPrice()))
                        .build();
        items.add(item);
        //return item;
        PreferenceBackUrlsRequest bu = PreferenceBackUrlsRequest.builder().success(urlSuccess).failure(urlFailure).pending(urlFailure).build();
        List<PreferencePaymentTypeRequest> excludedPaymentTypes = new ArrayList<>();
        excludedPaymentTypes.add(PreferencePaymentTypeRequest.builder().build().id("ticket").build());
        PreferencePaymentMethodsRequest paymentMethods = PreferencePaymentMethodsRequest.builder()
                .excludedPaymentTypes(excludedPaymentTypes)
                .installments(1)
                .build();

        PreferenceRequest request = PreferenceRequest.builder()
                .items(items)
                .paymentMethods(paymentMethods)
                .autoReturn("approved")
                .externalReference(itemMercadoPago.getCode())
                .backUrls(bu).build();

        //PreferenceRequest request = PreferenceRequest.builder().items(items).build();
    }

}
