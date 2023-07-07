package vrs.backend.demo.services.implementation;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Service;
import vrs.backend.demo.entities.MercadoPagoItem.ItemMercadoPago;
import vrs.backend.demo.entities.Pedido;
import vrs.backend.demo.enums.EstadoPedido;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.PedidoRepository;
import vrs.backend.demo.services.PedidoService;


import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl extends BaseServiceImpl<Pedido,Long> implements PedidoService {
    private PedidoRepository pedidoRepository;
    private String urlSuccess = "http://localhost:9000/mercadopago/success";
    private String urlFailure = "http://localhost:9000/mercadopago/failure";

    @PostConstruct
    public void initMPConfig(){
        MercadoPagoConfig.setAccessToken("TEST-5476971068198817-062719-e803d7431da3e762b7ef9734a87f762f-589247118");
    }
    public Preference crearPreferencia(ItemMercadoPago itemMercadoPago){
        try{
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

            return p;
        }
        catch (MPApiException | MPException e){
            System.out.println(e);
            return null;
        }
    }

    public PedidoServiceImpl(BaseRepository<Pedido, Long> baseRepository) {
        super(baseRepository);
    }

    //Cambiar estado de Envio
    public void cambiarEstadoEnvio(Long pedidoId, EstadoPedido estado) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(pedidoId);

        if (optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();
            pedido.setEstadoPedido(estado);
            pedidoRepository.save(pedido);
        }
    }
}
