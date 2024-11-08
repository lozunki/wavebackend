package org.lozunki.wavebackend.product.dao.repository.Impl;

import lombok.extern.slf4j.Slf4j;
import org.lozunki.wavebackend.product.dao.mapper.ProductMapper;
import org.lozunki.wavebackend.product.dao.repository.ProductDao;
import org.lozunki.wavebackend.product.entity.PO.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ProductDaoImpl implements ProductDao{

    @Autowired
    private ProductMapper productMapper;


    @Override
    public int addNew(Product product) {
        productMapper.addNew(product);
        return 1;
    }
}
