//package org.example.elasticsearchdemo.test;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.client.indices.CreateIndexRequest;
//import org.elasticsearch.client.indices.CreateIndexResponse;
//import org.elasticsearch.client.indices.GetIndexRequest;
//import org.elasticsearch.client.indices.GetIndexResponse;
//
//import java.io.IOException;
//
//public class ESTest_Index_Search {
//
//    public static void main(String[] args) throws IOException {
//
//        RestHighLevelClient esClient = new RestHighLevelClient(
//                RestClient.builder(new HttpHost("192.168.75.129", 9200, "http"))
//        );
//
//        GetIndexRequest request = new GetIndexRequest("user");
//        GetIndexResponse getIndexResponse = esClient.indices().get(request, RequestOptions.DEFAULT);
//
//        // 响应状态
//        System.out.println(getIndexResponse.getAliases());
//        System.out.println(getIndexResponse.getMappings());
//        System.out.println(getIndexResponse.getSettings());
//
//        esClient.close();
//
//    }
//
//}
