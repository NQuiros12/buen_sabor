package vrs.backend.demo.entities.projections;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public interface CostosGanancias {
    @JsonProperty("ganancias")
    Double getGanancias();
    @JsonProperty("costos")
    Double getCostos();
}
