package vrs.backend.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vrs.backend.demo.entities.FormaPago;
import vrs.backend.demo.generics.controllers.implementation.BaseControllerImpl;

import vrs.backend.demo.services.implementation.FormaPagoServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/formapagos")
public class FormaPagoController extends BaseControllerImpl<FormaPago, FormaPagoServiceImpl> {

}
