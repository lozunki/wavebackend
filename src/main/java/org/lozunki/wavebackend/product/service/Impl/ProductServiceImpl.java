package org.lozunki.wavebackend.product.service.Impl;

import org.lozunki.wavebackend.product.dao.mapper.ProductMapper;
import org.lozunki.wavebackend.product.dao.repository.ProductDao;
import org.lozunki.wavebackend.product.entity.PO.Product;
import org.lozunki.wavebackend.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public int addNewProduct(Product product) {
        // 使用 MyBatis-Plus 提供的 insert 方法
        return productMapper.insert(product);
    }
}
