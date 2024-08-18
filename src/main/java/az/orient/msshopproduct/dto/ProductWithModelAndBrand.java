package az.orient.msshopproduct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithModelAndBrand {
    private String productCode;
    private Double productPrice;
    private Long modelId;
    private String modelName;
    private String modelDescription;
    @JsonProperty("brandName")
    private String name;
    @JsonProperty("brandDescription")
    private String description;

}
