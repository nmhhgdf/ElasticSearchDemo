package org.example.es.esspringdemo;

import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import org.example.es.esspringdemo.test.Product;
import org.example.es.esspringdemo.test.ProductDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sound.sampled.Port;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SpringDataESSearchTest {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Test
    public void termQuery() {
        TermQuery termQuery = TermQuery.of(t -> t
                .field("category")
                .value("手机")
        );

        Query query = NativeQuery.builder()
                .withQuery(termQuery._toQuery())
                .build();

        SearchHits<Product> searchHits = elasticsearchOperations.search(query, Product.class);

        for (SearchHit<Product> hit : searchHits) {
            Product product = hit.getContent();
            System.out.println(product);
        }
    }

    @Test
    public void termQueryByPage(){
        int currentPage= 0 ;
        int pageSize = 5;
        //设置查询分页
        PageRequest pageRequest = PageRequest.of(currentPage, pageSize);

        TermQuery termQuery = TermQuery.of(t -> t
                .field("category")
                .value("手机")
        );

        // 构建 NativeQuery
        Query query = NativeQuery.builder()
                .withQuery(termQuery._toQuery())
                .withPageable(pageRequest)
                .build();

        // 执行查询
        SearchHits<Product> searchHits = elasticsearchOperations.search(query, Product.class);

        // 处理结果
        for (SearchHit<Product> hit : searchHits) {
            Product product = hit.getContent();
            System.out.println(product);
        }

        // 分页信息
        long totalHits = searchHits.getTotalHits();
        System.out.println("Total hits: " + totalHits);
        System.out.println("Current page: " + currentPage);
        System.out.println("Page size: " + pageSize);
        System.out.println("Total pages: " + (totalHits + pageSize - 1) / pageSize);
    }

}
