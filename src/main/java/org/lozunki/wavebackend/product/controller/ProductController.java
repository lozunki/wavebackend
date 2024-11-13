package org.lozunki.wavebackend.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.lozunki.wavebackend.common.web.Response;
import org.lozunki.wavebackend.product.entity.VO.ProductVO;
import org.lozunki.wavebackend.product.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/viewall")
    public Response<List<ProductVO>> getall() {
        List<ProductVO> products = productService.getAllProducts();
        Response<List<ProductVO>> response = new Response<>();
        response.setData(products);
        response.setState(200);
        response.setMessage("success");
        return response;
    }
}
