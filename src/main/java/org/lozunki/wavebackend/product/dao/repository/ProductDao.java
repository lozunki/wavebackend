package org.lozunki.wavebackend.product.dao.repository;

import org.lozunki.wavebackend.product.entity.PO.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAllProducts();
}
