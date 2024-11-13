package org.lozunki.wavebackend.product.dao.repository.Impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.lozunki.wavebackend.product.dao.mapper.ProductMapper;
import org.lozunki.wavebackend.product.dao.repository.ProductDao;
import org.lozunki.wavebackend.product.entity.PO.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<Product> getAllProducts() {
        return productMapper.selectList(null);
    }
}
