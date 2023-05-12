package vrs.backend.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vrs.backend.demo.entities.TipoEnvio;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;
import vrs.backend.demo.services.implementation.TipoEnvioServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/tipoenvios")
public class TipoEnvioController extends BaseControllerImpl<TipoEnvio, TipoEnvioServiceImpl> {

}
