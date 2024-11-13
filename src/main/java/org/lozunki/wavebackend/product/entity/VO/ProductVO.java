package org.lozunki.wavebackend.product.entity.VO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductVO {
    private Integer productId;
    private Integer parentId;
    private String productName;
    private String productImageUrl;  // 前端可以直接访问的图片URL
    private String productDescription;
    private BigDecimal productPrice;
}
