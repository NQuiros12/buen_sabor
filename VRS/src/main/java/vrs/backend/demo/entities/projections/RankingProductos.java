package vrs.backend.demo.entities.projections;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface RankingProductos {
    @JsonProperty("denominacion")
    String getDenominacion();
    @JsonProperty("countVentas")
    Integer getCountVentas();
}
