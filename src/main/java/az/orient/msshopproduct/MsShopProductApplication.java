package az.orient.msshopproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsShopProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsShopProductApplication.class, args);
    }

}
