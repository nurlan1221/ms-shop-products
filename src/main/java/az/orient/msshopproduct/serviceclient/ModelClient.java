package az.orient.msshopproduct.serviceclient;


import az.orient.msshopproduct.dto.GetProductByModelId;
import az.orient.msshopproduct.dto.ProductWithModelAndBrand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-shop-model", url = "${model.service.url}/models")
public interface ModelClient {
    @GetMapping(path = "{id}")
     ResponseEntity<GetProductByModelId> getModelById(@PathVariable Long id);

    @GetMapping("/by-brand/{brandId}")
     List<ProductWithModelAndBrand> getModelsByBrandId(@PathVariable Long brandId);
}
