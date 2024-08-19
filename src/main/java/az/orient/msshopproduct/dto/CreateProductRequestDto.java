package az.orient.msshopproduct.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequestDto {
    @NotNull(message = "Product code is required")
    private String productCode;
    private String productDescription;
    @NotNull(message = "Model id is required")
    private Long modelId;
    private Double price;
    @NotNull(message = "Category id is required")
    private Long categoryId;
    @NotNull(message = "Brand id is required")// For associating the product with a category// For associating the category with a parent category
    private Long brandId;
}
