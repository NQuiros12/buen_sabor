package vrs.backend.demo.entities.projections;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface TopClientes {
    @JsonProperty("emailUsuario")
    String getEmailUsuario();
    @JsonProperty("countCompras")
    Integer getCountCompras();
}
