package az.orient.msshopproduct.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryResponseDto {
    private String productCode;
    private Double productPrice;
    private String categoryName;
    private String parentCategoryName;
}
