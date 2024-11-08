package org.lozunki.wavebackend.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.lozunki.wavebackend.product.entity.PO.Product;
import org.lozunki.wavebackend.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    // 创建新的产品
    @PostMapping("/add")
    public String addNewProduct(@RequestBody Product product) {
        int result = productService.addNewProduct(product);
        return result > 0 ? "Product added successfully" : "Failed to add product";
    }
    @GetMapping("/addnew")
    public String addNewProduct() {
        return "addNewProduct";
    }
    @GetMapping("/delete")
    public String deleteProduct() {
        return "addNewProduct";
    }

}
