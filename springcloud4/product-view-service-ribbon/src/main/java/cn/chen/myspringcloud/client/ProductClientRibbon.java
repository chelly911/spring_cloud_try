package cn.chen.myspringcloud.client;

import cn.chen.myspringcloud.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ProductClientRibbon {

    @Autowired
    RestTemplate restTemplate;

    //PRODUCT-DATA-SERVICE是数据服务在eureka注册中心的名称
   public List<Product> listProducts() {
        return restTemplate.getForObject("http://PRODUCT-DATA-SERVICE/products",List.class);
    }
}
