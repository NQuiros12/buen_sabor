package vrs.backend.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vrs.backend.demo.entities.DetallePedido;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.DetallePedidoServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/detallepedidos")
public class DetallePedidoController extends BaseControllerImpl<DetallePedido, DetallePedidoServiceImpl> {
    DetallePedidoServiceImpl detallePedidoServiceImpl;
    public DetallePedidoController(DetallePedidoServiceImpl detallePedidoServiceImpl){
        this.detallePedidoServiceImpl = detallePedidoServiceImpl;
    }
    @PostMapping("/analitica/getBestProducts")
    public ResponseEntity<?> getBestProducts(@RequestBody Map<String, String> params) throws ParseException {
            String fecha1 = params.get("fecha1");
            String fecha2 = params.get("fecha2");

            Date fecha1Formateada = new SimpleDateFormat("yyyy-MM-dd").parse(fecha1);
            Date fecha2Formateada = new SimpleDateFormat("yyyy-MM-dd").parse(fecha2);

            try{
                return ResponseEntity.status(HttpStatus.OK).body(detallePedidoServiceImpl.rankingProductos(fecha1Formateada, fecha2Formateada));
            }catch(Exception e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay productos vendidos en esas fechas"+e.getMessage());
            }
    }
}
