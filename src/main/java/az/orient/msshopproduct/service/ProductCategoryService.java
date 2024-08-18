package az.orient.msshopproduct.service;

import az.orient.msshopproduct.dto.CreateProductRequestDto;
import az.orient.msshopproduct.dto.GetProductByModelId;
import az.orient.msshopproduct.dto.ProductCategoryResponseDto;
import az.orient.msshopproduct.dto.ProductWithModelAndBrand;
import az.orient.msshopproduct.entity.CategoryEntity;
import az.orient.msshopproduct.entity.ProductCategoryEntity;
import az.orient.msshopproduct.entity.ProductEntity;
import az.orient.msshopproduct.exception.BrandNotFoundException;
import az.orient.msshopproduct.exception.CategoryIdNotFoundException;
import az.orient.msshopproduct.repository.CategoryRepo;
import az.orient.msshopproduct.repository.ProductCategoryRepo;
import az.orient.msshopproduct.repository.ProductRepo;
import az.orient.msshopproduct.serviceclient.ModelClient;
import az.orient.msshopproduct.type.Status;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {
    private final ProductCategoryRepo productCategoryRepo;
    private final ModelClient modelClient;
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    public List<ProductCategoryResponseDto> getAllProductCategories() {
        return productCategoryRepo.findAllProductCategories();
    }

    public ProductCategoryResponseDto getProductCategoryById(Long id) {
        return productCategoryRepo.findByCategory_Id(id);
    }

    public List<GetProductByModelId> getProductByModelId(Long id) {
        List<ProductEntity> products = productRepo.findByModelId(id);
        return products.stream().map(product -> {
            GetProductByModelId model = modelClient.getModelById(id);
            return GetProductByModelId.builder()
                    .productCode(product.getProductCode())
                    .productPrice(product.getPrice())
                    .name(model.getName())
                    .description(model.getDescription())// Assuming `model.getName()` returns the model name
                    .build();
        }).collect(Collectors.toList());
    }

    public List<ProductWithModelAndBrand> getProductWithModelAndBrand(Long id) {
        // Fetch models by brandId using the ModelServiceClient
        List<ProductWithModelAndBrand> models = modelClient.getModelsByBrandId(id);
        if (models.isEmpty()) {
            throw new BrandNotFoundException("No product found with id " + id);
        }


        // Collect all model IDs
        List<Long> modelIds = models.stream()
                .map(ProductWithModelAndBrand::getModelId)
                .collect(Collectors.toList());

        // Fetch products by model IDs
        List<ProductEntity> products = productRepo.findByModelIdIn(modelIds);

        // Combine product, model, and brand information
        return products.stream().map(product -> {
            ProductWithModelAndBrand model = models.stream()
                    .filter(m -> m.getModelId().equals(product.getModelId()))
                    .findFirst()
                    .orElseThrow(() -> new ExpressionException("Model not found"));

            return ProductWithModelAndBrand.builder()
                    .productCode(product.getProductCode())
                    .productPrice(product.getPrice())
                    .modelName(model.getModelName())
                    .modelDescription(model.getModelDescription())
                    .modelId(model.getModelId())
                    .name(model.getName())
                    .description(model.getDescription())
                    .build();
        }).collect(Collectors.toList());
    }

    public void   createProduct(CreateProductRequestDto createProductRequestDto) {
        ProductEntity productEntity=new ProductEntity();
        productEntity.setProductCode(createProductRequestDto.getProductCode());
        productEntity.setModelId(createProductRequestDto.getModelId());
        productEntity.setPrice(createProductRequestDto.getPrice());
        productEntity.setStatus(Status.ACTIVE);
        productEntity=productRepo.save(productEntity);
        CategoryEntity categoryEntity=new CategoryEntity();
        categoryEntity.setId(createProductRequestDto.getCategoryId());
        ProductCategoryEntity productCategoryEntity=new ProductCategoryEntity();
        productCategoryEntity.setProduct(productEntity);
        productCategoryEntity.setCategory(categoryEntity);
        productCategoryEntity.setModelId(createProductRequestDto.getModelId());
        productCategoryEntity.setBrandId(createProductRequestDto.getBrandId());
        productCategoryRepo.save(productCategoryEntity);


    }

}





