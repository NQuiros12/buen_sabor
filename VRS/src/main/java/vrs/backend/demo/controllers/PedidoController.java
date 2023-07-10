package vrs.backend.demo.controllers;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import vrs.backend.demo.entities.*;
import vrs.backend.demo.enums.EstadoPedido;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.ArticuloInsumoServiceImpl;
import vrs.backend.demo.services.implementation.DetallePedidoServiceImpl;
import vrs.backend.demo.services.implementation.PedidoServiceImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping(path = "/pedidos")
@Transactional
public class PedidoController extends BaseControllerImpl<Pedido, PedidoServiceImpl> {

    private final DetallePedidoServiceImpl detallePedidoServiceImpl;
    private final PedidoServiceImpl pedidoServiceImpl;
    private final ArticuloInsumoServiceImpl articuloInsumoServiceImpl;
    private final ArticuloManufacturadoController articuloManufacturadoController;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Override
    @Transactional
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Pedido pedido) {
        try {
            // Validar si no se envían detalles de pedidos
            if (pedido.getDetallePedidos() == null || pedido.getDetallePedidos().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Debe proporcionar al menos un detalle de pedido.");
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
            return ResponseEntity.status(HttpStatus.OK).body(pedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/{id}")
    @Override
    @Transactional
    public ResponseEntity<?> update(@RequestBody Pedido pedidoRecibido, @PathVariable("id") Long id) {

        try {
            // Obtener el objeto Pedido existente
            Pedido pedidoPrevio = pedidoServiceImpl.findById(id);

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

            pedidoServiceImpl.update(id, pedidoPrevio);
            simpMessagingTemplate.convertAndSendToUser(pedidoPrevio.getCliente().getUsuario().getUsuario(),"/private",pedidoPrevio.getId());
            simpMessagingTemplate.convertAndSend("/pedidows/public", "Pedido actualizado");
            return ResponseEntity.ok("Pedido actualizado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el pedido: " + e.getMessage());
        }
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

}
