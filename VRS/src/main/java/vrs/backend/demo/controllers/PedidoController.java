package vrs.backend.demo.controllers;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import vrs.backend.demo.entities.*;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.PedidoServiceImpl;
import vrs.backend.demo.enums.EstadoPedido;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping(path = "/pedidos")
@Transactional
public class PedidoController extends BaseControllerImpl<Pedido, PedidoServiceImpl> {

    private final PedidoServiceImpl pedidoServiceImpl;
    @GetMapping("/byEstado/{estadoPedido}")
    public List<Pedido> pedidoByEstado(@PathVariable EstadoPedido estadoPedido) throws Exception {
        return pedidoServiceImpl.buscarPedidosEstado(estadoPedido);
    }
    @GetMapping("/preparedAndDelivered")
    public List<Pedido> pedidosChef(){
        return pedidoServiceImpl.buscarPedidosEstadoChef();
    }
    @GetMapping("/rejectedAndDelivered")
    public ResponseEntity<?> pedidosRejectedDelivered(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pedidoServiceImpl.pedidosRechazadosEntregados());
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al traer pedidos rechazados y entregados" + e);

        }

    }
    @GetMapping("/rejectedAndDeliveredPaged/{page}")
    public ResponseEntity<?> pedidosRejectedDeliveredPage(@PathVariable Integer page){
        try{
            Page<Pedido> pageResult = pedidoServiceImpl.PedidosByRechazadosEntregados(page);
            return ResponseEntity.status(HttpStatus.OK).body(pageResult);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al traer pedidos rechazados y entregados " + e);

        }
    }
    //Para todos los demas estados que no sean Rechazado y Entregado
    @GetMapping("/notRejectedAndDeliveredPaged/{page}")
    public ResponseEntity<?> pedidosNotRejectedNotDeliveredPage(@PathVariable Integer page){
        try{
            Page<Pedido> pageResult = pedidoServiceImpl.PedidosNotRechazadosEntregados(page);
            return ResponseEntity.status(HttpStatus.OK).body(pageResult);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al traer pedidos distintos rechazados y entregados " + e);

        }
    }
    @GetMapping("/byId/{idInput}")
    public ResponseEntity<?> pedidosById(@PathVariable Long idInput){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pedidoServiceImpl.pedidosById(idInput));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al buscar por ID"+e);
        }
    }
    @GetMapping("/byCliente/{idCliente}")
    public ResponseEntity<?> pedidosByCliente(@PathVariable Long idCliente){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pedidoServiceImpl.pedidosByCliente(idCliente));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.OK).body("Error al buscar por cliente" + e);
        }

    }
    @Override
    @Transactional
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Pedido pedido) {
        try {
            pedidoServiceImpl.savePedido(pedido);
            return ResponseEntity.status(HttpStatus.OK).body(pedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el pedido" + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Override
    @Transactional
    public ResponseEntity<?> update(@RequestBody Pedido pedidoRecibido, @PathVariable("id") Long id) {

        try {
            pedidoServiceImpl.updatePedido(pedidoRecibido, id);
            return ResponseEntity.ok("Pedido actualizado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el pedido: " + e.getMessage());
        }
    }
    //Analitica y estadistica
    @PostMapping("/analitica/pedidosByDay")
    public ResponseEntity<?> pedidosByDay(@RequestBody Map<String, String> params) throws ParseException {
        String fecha1 = params.get("fecha1");
        String fecha2 = params.get("fecha2");

        Date fecha1Formateada = new SimpleDateFormat("yyyy-MM-dd").parse(fecha1);
        Date fecha2Formateada = new SimpleDateFormat("yyyy-MM-dd").parse(fecha2);

        try{
            return ResponseEntity.status(HttpStatus.OK).body(pedidoServiceImpl.pedidosByDay(fecha1Formateada, fecha2Formateada));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran pedidos para esas fechas."+e.getMessage());
        }
    }
    @PostMapping("/analitica/topClientes")
    public ResponseEntity<?> topClientes(@RequestBody Map<String, String> params) throws ParseException {
        String fecha1 = params.get("fecha1");
        String fecha2 = params.get("fecha2");

        Date fecha1Formateada = new SimpleDateFormat("yyyy-MM-dd").parse(fecha1);
        Date fecha2Formateada = new SimpleDateFormat("yyyy-MM-dd").parse(fecha2);

        try{
            return ResponseEntity.status(HttpStatus.OK).body(pedidoServiceImpl.topClientes(fecha1Formateada, fecha2Formateada));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran clientes con compras para esas fechas. "+e.getMessage());
        }
    }
    @PostMapping("/analitica/costosGanancias")
    public ResponseEntity<?> costosGanancias(@RequestBody Map<String, String> params) throws ParseException {
        String fecha1 = params.get("fecha1");
        String fecha2 = params.get("fecha2");

        Date fecha1Formateada = new SimpleDateFormat("yyyy-MM-dd").parse(fecha1);
        Date fecha2Formateada = new SimpleDateFormat("yyyy-MM-dd").parse(fecha2);

        try{
            return ResponseEntity.status(HttpStatus.OK).body(pedidoServiceImpl.costosGananciasByDate(fecha1Formateada, fecha2Formateada));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran clientes con compras para esas fechas. "+e.getMessage());
        }
    }

    @PutMapping("/updateEstado/{id}/{estadoRecibido}")
    @Transactional
    public ResponseEntity<?> updateEstado(@PathVariable EstadoPedido estadoRecibido, @PathVariable("id") Long id) {
        try {
            pedidoServiceImpl.cambiarEstadoEnvio(id,estadoRecibido);
            return ResponseEntity.ok("Estado actualizado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el estado: " + e.getMessage());
        }
    }

    @GetMapping("/allCaja")
    public ResponseEntity<?> pedidosByCliente(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pedidoServiceImpl.PedidosNotRechazadosYEntregados());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.OK).body("Error al buscar por cliente" + e);
        }
    }

    @GetMapping("/allByDelivery/{idAuth0}")
    public ResponseEntity<?> pedidosByDelivery(@PathVariable String idAuth0){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pedidoServiceImpl.pedidosByUsuarioEntrega(idAuth0));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.OK).body("Error al buscar por cliente" + e);
        }
    }

    @PutMapping("/updatePago/{id}/{pagoConfirmado}")
    @Transactional
    public ResponseEntity<?> updatePago(@PathVariable boolean pagoConfirmado, @PathVariable("id") Long id) {
        try {
            pedidoServiceImpl.cambiarPagoConfirmado(id,pagoConfirmado);
            return ResponseEntity.ok("Pago actualizado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el pago: " + e.getMessage());
        }
    }

    @PutMapping("/updateEstadoEntrega/{id}/{estadoRecibido}/{idAuth0}")
    @Transactional
    public ResponseEntity<?> updateEstadoEntrega(@PathVariable EstadoPedido estadoRecibido, @PathVariable("id") Long id, @PathVariable String idAuth0) {
        try {
            pedidoServiceImpl.cambiarEstadoEnvioEntrega(id,estadoRecibido,idAuth0);
            return ResponseEntity.ok("Estado actualizado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el estado: " + e.getMessage());
        }
    }


}
