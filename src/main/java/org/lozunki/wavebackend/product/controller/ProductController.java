package org.lozunki.wavebackend.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.lozunki.wavebackend.common.web.Response;
import org.lozunki.wavebackend.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/viewall")
    public Response getall() {
        Response response = new Response();
        response.setData(1);
        response.setState(200);
        response.setMessage("success");
        return response;
    }

    @GetMapping("/viewtitle")
    public Response viewtitle() {
        Response response = new Response();
        response.setData(1);
        response.setState(200);
        response.setMessage("success");
        return response;
    }
}
