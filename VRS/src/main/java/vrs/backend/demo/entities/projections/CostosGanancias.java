package vrs.backend.demo.entities.projections;

import com.fasterxml.jackson.annotation.JsonProperty;



public interface CostosGanancias {
    @JsonProperty("ganancias")
    Double getGanancias();
    @JsonProperty("costos")
    Double getCostos();
}
