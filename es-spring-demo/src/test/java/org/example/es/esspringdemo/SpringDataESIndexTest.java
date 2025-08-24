package org.example.es.esspringdemo;

import org.example.es.esspringdemo.test.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SpringDataESIndexTest {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Test
    public void createIndex() {
        // 创建索引，系统初始化会自动创建索引
        System.out.println("创建索引");
    }

    @Test
    public void deleteIndex() {
        // 删除索引
        boolean flg = elasticsearchOperations.indexOps(Product.class).delete();
        System.out.println("删除索引 = " + flg);
    }
}
