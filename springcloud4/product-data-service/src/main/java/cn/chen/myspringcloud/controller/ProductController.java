package cn.chen.myspringcloud.controller;

import cn.chen.myspringcloud.pojo.Product;
import cn.chen.myspringcloud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping("/products")
    public Object products(){
        List<Product> ps = productService.listProducts();
        return ps;
    }
}
