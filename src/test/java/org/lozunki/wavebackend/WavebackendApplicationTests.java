package org.lozunki.wavebackend;

import org.junit.jupiter.api.Assertions;
import org.lozunki.wavebackend.product.dao.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.lozunki.wavebackend.product.entity.PO.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WavebackendApplicationTests {
    @Autowired
    private ProductMapper productMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testDatabaseConnection() {
        // 尝试执行一个简单的查询以测试数据库连接是否正常
        List<Product> products = productMapper.selectList(null);

        // 检查查询结果，如果连接成功并且表中有数据，结果应该不是null
        Assertions.assertNotNull(products, "数据库连接失败，未能获取到数据");
        System.out.println("数据库连接成功，查询到的记录数：" + products.size());
    }

}
