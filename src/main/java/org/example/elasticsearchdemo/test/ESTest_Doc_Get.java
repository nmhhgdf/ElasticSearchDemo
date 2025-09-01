//package org.example.elasticsearchdemo.test;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.action.update.UpdateResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.xcontent.XContentType;
//
//import java.io.IOException;
//
//public class ESTest_Doc_Get {
//
//    public static void main(String[] args) throws IOException {
//
//        RestHighLevelClient esClient = new RestHighLevelClient(
//                RestClient.builder(new HttpHost("192.168.75.129", 9200, "http"))
//        );
//
//        GetRequest request = new GetRequest();
//        request.index("user").id("1001");
//        GetResponse response = esClient.get(request, RequestOptions.DEFAULT);
//
//        System.out.println(response.getSourceAsMap());
//
//        esClient.close();
//
//    }
//
//}
