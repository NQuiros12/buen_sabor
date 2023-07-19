package vrs.backend.demo.entities.projections;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface TopClientes {
    @JsonProperty("nombreCompleto")
    String getNombreCompleto();
    @JsonProperty("countCompras")
    Integer getCountCompras();
}
