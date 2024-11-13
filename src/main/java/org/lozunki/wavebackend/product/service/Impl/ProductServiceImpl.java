package org.lozunki.wavebackend.product.service.Impl;

import org.lozunki.wavebackend.product.dao.mapper.ProductMapper;
import org.lozunki.wavebackend.product.dao.repository.ProductDao;
import org.lozunki.wavebackend.product.entity.DTO.ParentProductNameDTO;
import org.lozunki.wavebackend.product.entity.PO.Product;
import org.lozunki.wavebackend.product.entity.VO.ProductVO;
import org.lozunki.wavebackend.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    private ProductDao productDao;

    @Value("${wavebackend.upload.host}")
    private String host;
    @Value("${wavebackend.upload.base-dir-name}")
    private String baseDirName;


    @Override
    public List<ProductVO> getAllProducts() {
        List<Product> products = productMapper.selectList(null);
        return products.stream().map(product -> {
                    ProductVO productVO = new ProductVO();
                    productVO.setProductId(product.getProductId());
                    productVO.setParentId(product.getParentId());
                    productVO.setProductName(product.getProductName());
                    productVO.setProductDescription(product.getProductDescription());
                    productVO.setProductPrice(product.getProductPrice());
                    String imageUrl = host + baseDirName + product.getProductImageUrl();
                    productVO.setProductImageUrl(imageUrl);
                    return productVO;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public List<ParentProductNameDTO> getParentAndChildProductNames() {
        return List.of();
    }
}
