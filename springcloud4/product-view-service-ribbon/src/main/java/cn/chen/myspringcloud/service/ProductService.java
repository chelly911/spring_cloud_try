package cn.chen.myspringcloud.service;

import cn.chen.myspringcloud.client.ProductClientRibbon;
import cn.chen.myspringcloud.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductClientRibbon productClientRibbon;

    public List<Product> listProduct(){
        return productClientRibbon.listProducts();
    }
}
