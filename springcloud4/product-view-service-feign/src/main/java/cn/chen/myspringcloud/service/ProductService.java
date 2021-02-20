package cn.chen.myspringcloud.service;

import cn.chen.myspringcloud.client.ProductClientFeign;
import cn.chen.myspringcloud.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductClientFeign productClientFeign;

    public List<Product> listProducts(){
        return productClientFeign.listProducts();
    }
}
