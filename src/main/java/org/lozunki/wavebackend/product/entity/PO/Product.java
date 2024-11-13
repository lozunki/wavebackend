package org.lozunki.wavebackend.product.entity.PO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("product")
public class Product {

    @TableId(value = "product_id", type = IdType.AUTO) // 主键
    private Integer productId;

    private Integer parentId; // 父级 ID

    private String productName; // 产品名称

    private String productImageUrl; // 产品图片 URL

    private String productDescription; // 产品描述

    private BigDecimal productPrice; // 产品价格

}
