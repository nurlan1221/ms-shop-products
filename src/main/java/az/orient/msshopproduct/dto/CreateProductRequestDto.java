package az.orient.msshopproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequestDto {
    private String productCode;
    private String productDescription;
    private Long modelId;
    private Double price;
    private String categoryName;
    private Long categoryId;       // For associating the product with a category// For associating the category with a parent category
    private Long brandId;
}
