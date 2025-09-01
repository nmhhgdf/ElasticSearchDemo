//package org.example.elasticsearchdemo.test;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.client.indices.CreateIndexRequest;
//import org.elasticsearch.client.indices.CreateIndexResponse;
//
//import java.io.IOException;
//
//public class ESTest_Index_Create {
//
//    public static void main(String[] args) throws IOException {
//
//        RestHighLevelClient esClient = new RestHighLevelClient(
//                RestClient.builder(new HttpHost("192.168.75.129", 9200, "http"))
//        );
//
//        CreateIndexRequest request = new CreateIndexRequest("user");
//        CreateIndexResponse createIndexResponse = esClient.indices().create(request, RequestOptions.DEFAULT);
//
//        // 响应状态
//        boolean acknowledged = createIndexResponse.isAcknowledged();
//        System.out.println("索引操作：" + acknowledged);
//
//        esClient.close();
//
//    }
//
//}
