package org.lozunki.wavebackend.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/addnew")
    public String addNewProduct() {
        log.error("12121212121212123423543253453");
        return "addNewProduct";
    }
}
