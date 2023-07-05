package vrs.backend.demo.controllers;

import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import vrs.backend.demo.entities.MercadoPagoItem.ItemMercadoPago;
import vrs.backend.demo.enums.EstadoPedido;
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
        PreferenceClient client = new PreferenceClient();

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
        //excludedPaymentTypes.add(PreferencePaymentTypeRequest.builder().build().id("ticket").build());
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

        Preference p = client.create(request);
        String prefId = p.getId();

        return ResponseEntity.status(HttpStatus.OK).body("{\"preferenceId\":\""+prefId+"\"}");
    }

    @GetMapping("/success")
    public RedirectView success(
            HttpServletRequest request,
            @RequestParam("collection_id") String collectionId,
            @RequestParam("collection_status") String collectionStatus,
            @RequestParam("external_reference") String externalReference,
            @RequestParam("payment_type") String paymentType,
            @RequestParam("merchant_order_id") String merchantOrderId,
            @RequestParam("preference_id") String preferenceId,
            @RequestParam("site_id") String siteId,
            @RequestParam("processing_mode") String processingMode,
            @RequestParam("merchant_account_id") String merchantAccountId,
            RedirectAttributes attributes)
            throws MPException {

        attributes.addFlashAttribute("genericResponse", true);
        attributes.addFlashAttribute("collection_id", collectionId);
        attributes.addFlashAttribute("collection_status", collectionStatus);
        attributes.addFlashAttribute("external_reference", externalReference);
        attributes.addFlashAttribute("payment_type", paymentType);
        attributes.addFlashAttribute("merchant_order_id", merchantOrderId);
        attributes.addFlashAttribute("preference_id",preferenceId);
        attributes.addFlashAttribute("site_id",siteId);
        attributes.addFlashAttribute("processing_mode",processingMode);
        attributes.addFlashAttribute("merchant_account_id",merchantAccountId);

        pedidoService.cambiarEstadoEnvio(Long.valueOf(externalReference), EstadoPedido.ENTREGADO);
        return new RedirectView("http://localhost:3000/"+externalReference +"?success=true");
    }

    @GetMapping("/failure")
    public RedirectView failure(
            HttpServletRequest request,
            @RequestParam("collection_id") String collectionId,
            @RequestParam("collection_status") String collectionStatus,
            @RequestParam("external_reference") String externalReference,
            @RequestParam("payment_type") String paymentType,
            @RequestParam("merchant_order_id") String merchantOrderId,
            @RequestParam("preference_id") String preferenceId,
            @RequestParam("site_id") String siteId,
            @RequestParam("processing_mode") String processingMode,
            @RequestParam("merchant_account_id") String merchantAccountId,
            RedirectAttributes attributes)
            throws MPException {

        attributes.addFlashAttribute("genericResponse", true);
        attributes.addFlashAttribute("collection_id", collectionId);
        attributes.addFlashAttribute("collection_status", collectionStatus);
        attributes.addFlashAttribute("external_reference", externalReference);
        attributes.addFlashAttribute("payment_type", paymentType);
        attributes.addFlashAttribute("merchant_order_id", merchantOrderId);
        attributes.addFlashAttribute("preference_id",preferenceId);
        attributes.addFlashAttribute("site_id",siteId);
        attributes.addFlashAttribute("processing_mode",processingMode);
        attributes.addFlashAttribute("merchant_account_id",merchantAccountId);

        try {//polemico
            pedidoService.delete(Long.valueOf(externalReference));
        }catch (Exception e){
            System.out.println("No se pudo eliminar la orden");
        }

        return new RedirectView("https://el-buen-sabor-frontend.vercel.app/cart?failure=true");
    }
}
