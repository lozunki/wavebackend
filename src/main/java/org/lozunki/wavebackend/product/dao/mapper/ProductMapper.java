package org.lozunki.wavebackend.product.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.lozunki.wavebackend.product.entity.PO.Product;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    void addNew(Product product);
}
