package vrs.backend.demo.services.implementation;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import vrs.backend.demo.controllers.ArticuloManufacturadoController;
import vrs.backend.demo.entities.*;
import vrs.backend.demo.entities.MercadoPagoItem.ItemMercadoPago;
import vrs.backend.demo.entities.projections.TopClientes;
import vrs.backend.demo.enums.EstadoPedido;
import vrs.backend.demo.generics.repositories.BaseRepository;
import vrs.backend.demo.generics.services.implementation.BaseServiceImpl;
import vrs.backend.demo.repositories.PedidoRepository;
import vrs.backend.demo.services.PedidoService;


import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

@Service
public class PedidoServiceImpl extends BaseServiceImpl<Pedido,Long> implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final DetallePedidoServiceImpl detallePedidoServiceImpl;
    private final ArticuloInsumoServiceImpl articuloInsumoServiceImpl;
    private final ArticuloManufacturadoController articuloManufacturadoController;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    private final String urlSuccess = "http://localhost:9000/mercadopago/success";
    private final String urlFailure = "http://localhost:9000/mercadopago/failure";
    @Value("${paged.size}")
    private int pagedSize;
    public PedidoServiceImpl(BaseRepository<Pedido, Long> baseRepository,
                             PedidoRepository pedidoRepository,
                             DetallePedidoServiceImpl detallePedidoServiceImpl,
                             ArticuloInsumoServiceImpl articuloInsumoServiceImpl,
                             ArticuloManufacturadoController articuloManufacturadoController) {
        super(baseRepository);
        this.pedidoRepository = pedidoRepository;
        this.detallePedidoServiceImpl = detallePedidoServiceImpl;
        this.articuloInsumoServiceImpl = articuloInsumoServiceImpl;
        this.articuloManufacturadoController = articuloManufacturadoController;
    }
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

            return p;
        }
        catch (MPApiException | MPException e){
            System.out.println(e);
            return null;
        }
    }


    //Cambiar estado de Envio
    public void cambiarEstadoEnvio(Long pedidoId, EstadoPedido estado) throws Exception {
        try {
            Optional<Pedido> optionalPedido = pedidoRepository.findById(pedidoId);

            if (optionalPedido.isPresent()) {
                Pedido pedido = optionalPedido.get();
                pedido.setEstadoPedido(estado);
                pedidoRepository.save(pedido);
                simpMessagingTemplate.convertAndSend("/pedidows/public", "Estado Actualizado");
            } else {
                throw new Exception("No se encontro el pedido");
            }
        } catch (Exception e){
            throw new Exception("No se pudo actualizar estado pedido"+e);
        }
    }
    public void savePedido(Pedido pedido) throws Exception{
        // Validar si no se envían detalles de pedidos
        if (pedido.getDetallePedidos() == null || pedido.getDetallePedidos().isEmpty()) {
            throw new Exception("Debe proporcionar al menos un detalle del pedido");
        }
        // Guardar el pedido principal
        super.save(pedido);
        // Asignar el ID del pedido a los detalles
        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            detalle.setPedido(pedido);
        }
        // Guardar los detalles

        detallePedidoServiceImpl.saveAll(pedido.getDetallePedidos());

        simpMessagingTemplate.convertAndSend("/pedidows/public", "Nuevo pedido");
    }
    public void updatePedido(Pedido pedidoRecibido, Long id) throws Exception{
        // Obtener el objeto Pedido existente
        Pedido pedidoPrevio = findById(id);

        if (pedidoPrevio == null) {
            throw new Exception("No se encontro el pedido");
        }

        // Validar si no se envían detalles de pedidos
        if (pedidoRecibido.getDetallePedidos() == null || pedidoRecibido.getDetallePedidos().isEmpty()) {
            throw new Exception("Debe proporcionar al menos un detalle de pedido.");
        }

        if (pedidoRecibido.getEstadoPedido() == EstadoPedido.PREPARACION ) {
            try {
                validarStockInsumos(pedidoRecibido);
                actualizarStockInsumos(pedidoRecibido);
            } catch (Exception e) {
                e.printStackTrace();
                // También puedes lanzar una excepción más específica si es necesario
                throw new Exception("Error al validar o actualizar el stock de ingredientes ", e);
            }
        }
        // Actualizar el pedido previo
        actualizarPedidoPrevio(pedidoPrevio, pedidoRecibido);

        // Actualizar los detalles del pedido
        actualizarDetallesPedido(pedidoPrevio, pedidoRecibido);
        // Guardar los cambios en la base de datos

        update(id, pedidoPrevio);
        simpMessagingTemplate.convertAndSendToUser(pedidoPrevio.getCliente().getUsuario().getUsuario(),"/private",pedidoPrevio.getId());
        simpMessagingTemplate.convertAndSend("/pedidows/public", "Pedido actualizado");
    }
    private void validarStockInsumos(Pedido pedido) throws Exception {
        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            // Obtén el producto del detalle
            ArticuloManufacturado articulo = detalle.getArticuloManufacturado();

            // Calcula la cantidad total de ingredientes requeridos
            double cantidadTotal = detalle.getCantidad();

            // Verifica el stock disponible para cada ingrediente
            if (articulo.isProductoFinal()) {
                double stockDisponible = articulo.getStockActual();

                if (stockDisponible < cantidadTotal) {
                    // Maneja la situación donde el stock es insuficiente
                    throw new Exception("El stock del producto '" + articulo.getDenominacion() + "' es insuficiente.");
                }
            } else {
                for (DetalleArticuloManufacturado detalleArticulo : articulo.getDetalleArticuloManufacturados()) {
                    ArticuloInsumo insumo = detalleArticulo.getArticuloInsumo();

                    double cantidadRequerida = cantidadTotal * detalleArticulo.getCantidad();
                    double stockDisponible = insumo.getStockActual();

                    if (stockDisponible < cantidadRequerida) {
                        // Maneja la situación donde el stock es insuficiente
                        throw new Exception("El stock del ingrediente '" + insumo.getDenominacion() + "' es insuficiente.");
                    }
                }
            }
        }
    }

    private void actualizarStockInsumos(Pedido pedido) throws Exception {
        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            // Obtén el producto del detalle
            ArticuloManufacturado articulo = detalle.getArticuloManufacturado();

            // Calcula la cantidad total de ingredientes requeridos
            double cantidadTotal = detalle.getCantidad();

            // Verifica el stock disponible para cada ingrediente
            if (articulo.isProductoFinal()) {
                double stockDisponible = articulo.getStockActual();
                System.out.println(stockDisponible- cantidadTotal);
                articulo.setStockActual(stockDisponible- cantidadTotal);
                if (articulo.getStockActual() < articulo.getStockMinimo()) {
                    articulo.setAltaBaja(false);
                }
                articuloManufacturadoController.update(articulo,articulo.getId());
            } else {
                for (DetalleArticuloManufacturado detalleArticulo : articulo.getDetalleArticuloManufacturados()) {
                    ArticuloInsumo insumo = detalleArticulo.getArticuloInsumo();

                    double cantidadRequerida = cantidadTotal * detalleArticulo.getCantidad();
                    double stockDisponible = insumo.getStockActual();

                    insumo.setStockActual(stockDisponible - cantidadRequerida);
                    if (insumo.getStockActual() < insumo.getStockMinimo()) {
                        insumo.setAltaBaja(false);
                        articulo.setAltaBaja(false);
                    }
                    articuloManufacturadoController.update(articulo,articulo.getId());
                    articuloInsumoServiceImpl.update(insumo.getId(), insumo);

                }
            }
        }
    }


    private void actualizarPedidoPrevio(Pedido pedidoPrevio, Pedido pedidoRecibido) {
        Field[] fields = pedidoRecibido.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(pedidoRecibido);

                if (!field.getName().equals("detallePedidos") && !field.getName().equals("id")) {
                    field.set(pedidoPrevio, value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void actualizarDetallesPedido(Pedido pedidoPrevio, Pedido pedidoRecibido) throws Exception {
        List<DetallePedido> detalles = new ArrayList<>();
        Iterator<DetallePedido> detallesRecibidos = pedidoRecibido.getDetallePedidos().iterator();

        while (detallesRecibidos.hasNext()) {
            DetallePedido detalleRecibido = detallesRecibidos.next();
            DetallePedido detalleActualizado;

            // Si el detalle ya existe, se actualiza
            if (detalleRecibido.getId() != null) {
                detalleActualizado = detallePedidoServiceImpl.findById(detalleRecibido.getId());
                if (detalleActualizado == null) {
                    throw new IllegalArgumentException("El detalle no existe.");
                }

                // Actualizar los datos del detalle
                detalleActualizado.setCantidad(detalleRecibido.getCantidad());
                detalleActualizado.setSubtotal(detalleRecibido.getSubtotal());
                detalleActualizado.setArticuloManufacturado(detalleRecibido.getArticuloManufacturado());
            } else {
                // Si el detalle es nuevo, se crea
                detalleActualizado = new DetallePedido();
                detalleActualizado.setCantidad(detalleRecibido.getCantidad());
                detalleActualizado.setSubtotal(detalleRecibido.getSubtotal());
                detalleActualizado.setPedido(pedidoPrevio);
                detalleActualizado.setArticuloManufacturado(detalleRecibido.getArticuloManufacturado());
            }

            detalles.add(detalleActualizado);
        }

        // Eliminar los detalles que existían pero no se recibieron en el JSON
        List<DetallePedido> detallesActuales = pedidoPrevio.getDetallePedidos();
        Iterator<DetallePedido> actualIterator = detallesActuales.iterator();

        while (actualIterator.hasNext()) {
            DetallePedido detalleActual = actualIterator.next();

            if (!detalles.contains(detalleActual)) {
                detallePedidoServiceImpl.delete(detalleActual.getId());
                actualIterator.remove();
            }
        }
        pedidoPrevio.setDetallePedidos(detalles);
    }
    public List<Pedido> buscarPedidosEstado(EstadoPedido estadoPedido) throws Exception { //corregir Exception para que se mas claro
        if (estadoPedido != null) {
            return pedidoRepository.pedidosByState(estadoPedido);
        }
        else{
            throw new Exception("Estado pedido se encuentra vacio");
        }
    }
    //En este caso no recibe parametros por que se trata de dos estados que son constantes
    public List<Pedido> buscarPedidosEstadoChef()  {
        return pedidoRepository.pedidosBy2States(EstadoPedido.PREPARADO,EstadoPedido.RECHAZADO);
    }
    //Rechazados y entregados
    //Sin paginacion
    public List<Pedido> pedidosRechazadosEntregados(){
        return pedidoRepository.pedidosBy2States(EstadoPedido.RECHAZADO,EstadoPedido.ENTREGADO);
    }
    public List<Pedido> pedidosById(long idInput){
        return pedidoRepository.pedidosById(idInput);
    }
    public List<Pedido> pedidosByCliente(Long idCliente){
        return pedidoRepository.pedidosByCliente(idCliente);
    }
    //Con paginación
    //RECHAZADOS Y ENTREGADOS
    public Page<Pedido> PedidosByRechazadosEntregados(Integer page){
        Pageable pageable = PageRequest.of(page, pagedSize);
        //En este caso solo se recibe como parametro el tamaño de la paginacion.
        //Los dos estados una vez mas son constantes
        List<Pedido> pedidos_rech_entr = pedidoRepository.pedidosBy2States(EstadoPedido.RECHAZADO,EstadoPedido.ENTREGADO);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), pedidos_rech_entr.size());
        List<Pedido> subList = pedidos_rech_entr.subList(start, end);
        Page<Pedido> pageResult = new PageImpl<>(subList, pageable, pedidos_rech_entr.size());

        return pageResult;
    }
    public Page<Pedido> PedidosNotRechazadosEntregados(Integer page){
        Pageable pageable = PageRequest.of(page, pagedSize);
        //En este caso solo se recibe como parametro el tamaño de la paginacion.
        //Los dos estados una vez mas son constantes
        List<Pedido> pedidos_rech_entr = pedidoRepository.pedidosNot2States(EstadoPedido.RECHAZADO,EstadoPedido.ENTREGADO);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), pedidos_rech_entr.size());
        List<Pedido> subList = pedidos_rech_entr.subList(start, end);
        Page<Pedido> pageResult = new PageImpl<>(subList, pageable, pedidos_rech_entr.size());

        return pageResult;
    }
    //Analitica y Estadistica
    public List<Integer> pedidosByDay(Date diaIn, Date diaEnd){
        return pedidoRepository.pedidosByDay(diaIn,diaEnd);
    }
    //Clientes rankeados
    public List<TopClientes> topClientes(Date diaIn, Date diaEnd){
        return pedidoRepository.topClientes(diaIn,diaEnd);
    }
}
