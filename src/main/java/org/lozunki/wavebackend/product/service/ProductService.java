package org.lozunki.wavebackend.product.service;

import org.lozunki.wavebackend.product.entity.DTO.ParentProductNameDTO;
import org.lozunki.wavebackend.product.entity.VO.ProductVO;

import java.util.List;

public interface ProductService {
    List<ProductVO> getAllProducts();

    List<ParentProductNameDTO> getParentAndChildProductNames();
}
