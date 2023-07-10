package vrs.backend.demo.enums;

public enum EstadoPedido {
    ENTREGADO,
    CAMINO ,
    //Pendiente hace referencia a pendiente de aprobacion por la caja
    PENDIENTE,
    //Aprobacion cocina
    ESPERA,
    PREPARACION, //Cocinando...
    PREPARADO,//Ya esta listo para enviar
    RECHAZADO
}
