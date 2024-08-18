package az.orient.msshopproduct.serviceclient;


import az.orient.msshopproduct.dto.GetProductByModelId;
import az.orient.msshopproduct.dto.ProductWithModelAndBrand;
import az.orient.msshopproduct.dto.RespModelBrand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-shop-model", url = "${model.service.url}/models")
public interface ModelClient {
    @GetMapping(path = "{id}")
    public GetProductByModelId getModelById(@PathVariable Long id);

    @GetMapping("/by-brand/{brandId}")
    public List<ProductWithModelAndBrand> getModelsByBrandId(@PathVariable Long brandId);


}
