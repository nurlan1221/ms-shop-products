package az.orient.msshopproduct.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespModelBrand {
    private String name;
    private String description;
    private Long modelId;
    private String modelName;

    private String modelDescription;

}
