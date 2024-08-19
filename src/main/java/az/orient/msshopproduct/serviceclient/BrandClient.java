package az.orient.msshopproduct.serviceclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-shop-brand",url = "${brand.service.url}/brands")
public interface BrandClient {
    @GetMapping(path = "{id}")
     ResponseEntity getBrandById(@PathVariable Long id);
}
