package az.orient.msshopproduct.controller;

import az.orient.msshopproduct.dto.CreateProductRequestDto;
import az.orient.msshopproduct.dto.GetProductByModelId;
import az.orient.msshopproduct.dto.ProductCategoryResponseDto;
import az.orient.msshopproduct.dto.ProductWithModelAndBrand;
import az.orient.msshopproduct.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("productCategories")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;


    @GetMapping
    public List<ProductCategoryResponseDto> getProductCategories() {
        return productCategoryService.getAllProductCategories();
    }
    @GetMapping(path = "{id}")
    public ProductCategoryResponseDto getProductCategoryById(@PathVariable Long id) {
       return productCategoryService.getProductCategoryById(id);
    }
    @GetMapping("/by-model/{modelId}")
    public List<ProductWithModelAndBrand> getProductWithModelAndBrand(@PathVariable Long modelId) {
        return productCategoryService.getProductWithModelAndBrand(modelId);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createProduct(@RequestBody CreateProductRequestDto createProductRequestDto){
        productCategoryService.createProduct(createProductRequestDto);
    }





}
