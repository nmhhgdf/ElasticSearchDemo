//package org.example.elasticsearchdemo.test;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.http.HttpHost;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.client.indices.GetIndexRequest;
//import org.elasticsearch.client.indices.GetIndexResponse;
//import org.elasticsearch.common.xcontent.XContentType;
//
//import java.io.IOException;
//
//public class ESTest_Doc_Insert {
//
//    public static void main(String[] args) throws IOException {
//
//        RestHighLevelClient esClient = new RestHighLevelClient(
//                RestClient.builder(new HttpHost("192.168.75.129", 9200, "http"))
//        );
//
//        IndexRequest request = new IndexRequest();
//        request.index("user").id("1001");
//
//        User user = new User();
//        user.setName("zhangsan");
//        user.setAge(30);
//        user.setSex("男");
//
//        ObjectMapper mapper = new ObjectMapper();
//        String userJson = mapper.writeValueAsString(user);
//        request.source(userJson, XContentType.JSON);
//
//        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);
//        System.out.println(response.getResult());
//
//        esClient.close();
//
//    }
//
//}
