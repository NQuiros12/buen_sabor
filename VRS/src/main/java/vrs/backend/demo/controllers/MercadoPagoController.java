package vrs.backend.demo.controllers;


import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import vrs.backend.demo.entities.MercadoPagoItem.ItemMercadoPago;
import vrs.backend.demo.enums.EstadoPedido;

import vrs.backend.demo.services.implementation.PedidoServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "mercadopago")
public class MercadoPagoController {
    private PedidoServiceImpl pedidoService;

    public MercadoPagoController(PedidoServiceImpl pedidoService){
        this.pedidoService = pedidoService;

    }

    @PostMapping("/createRedirect")
    public ResponseEntity<?> createRedirect(@RequestBody ItemMercadoPago itemMercadoPago) throws MPException, MPApiException,InterruptedException{
            try{
                Preference preference = pedidoService.crearPreferencia(itemMercadoPago);
                return ResponseEntity.ok().body(preference);
            }catch(Exception e){
                e.printStackTrace();
                return ResponseEntity.internalServerError().body("Error al crear preferencia"+e.getMessage());
            }
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
            throws Exception {

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

//        pedidoService.cambiarEstadoEnvio(Long.valueOf(externalReference), EstadoPedido.ENTREGADO);
        return new RedirectView("http://localhost:5173/"+externalReference +"?success=true");
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

//        try {//polemico
//            pedidoService.delete(Long.valueOf(externalReference));
//        }catch (Exception e){
//            System.out.println("No se pudo eliminar la orden");
//        }

        return new RedirectView("http://localhost:5173/"+externalReference +"?success=false");
    }
}
