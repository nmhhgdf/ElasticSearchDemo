//package org.example.elasticsearchdemo.test;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.sort.SortOrder;
//
//import java.io.IOException;
//
//public class ESTest_Doc_Query {
//
//    public static void main(String[] args) throws IOException {
//
//        RestHighLevelClient esClient = new RestHighLevelClient(
//                RestClient.builder(new HttpHost("192.168.75.129", 9200, "http"))
//        );
//
//        // 全量查询
//        SearchRequest request = new SearchRequest();
//        request.indices("user");
//
//        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        request.source(query);
//
//        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
//
//        SearchHits hits = response.getHits();
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }
//
//        // 条件查询
//        SearchRequest request2 = new SearchRequest();
//        request2.indices("user");
//
//        SearchSourceBuilder query2 = new SearchSourceBuilder().query(
//                QueryBuilders.termQuery("age", 30));
//        request2.source(query2);
//
//        SearchResponse response2 = esClient.search(request2, RequestOptions.DEFAULT);
//
//        SearchHits hits2 = response2.getHits();
//        System.out.println(hits2.getTotalHits());
//        System.out.println(response2.getTook());
//
//        for (SearchHit hit : hits2) {
//            System.out.println(hit.getSourceAsString());
//        }
//
//        // 分页查询
//        SearchRequest request3 = new SearchRequest();
//        request3.indices("user");
//
//        SearchSourceBuilder query3 = new SearchSourceBuilder().query(
//                QueryBuilders.matchAllQuery());
//        query3.from(0);
//        query3.size(2);
//
//        request3.source(query3);
//
//        SearchResponse response3 = esClient.search(request3, RequestOptions.DEFAULT);
//
//        SearchHits hits3 = response3.getHits();
//        System.out.println(hits3.getTotalHits());
//        System.out.println(response3.getTook());
//
//        for (SearchHit hit : hits3) {
//            System.out.println(hit.getSourceAsString());
//        }
//
//        // 查询排序
//        SearchRequest request4 = new SearchRequest();
//        request4.indices("user");
//
//        SearchSourceBuilder query4 = new SearchSourceBuilder().query(
//                QueryBuilders.matchAllQuery());
//        query4.sort("age", SortOrder.DESC);
//
//        request4.source(query4);
//
//        SearchResponse response4 = esClient.search(request4, RequestOptions.DEFAULT);
//
//        SearchHits hits4 = response4.getHits();
//        System.out.println(hits4.getTotalHits());
//        System.out.println(response4.getTook());
//
//        for (SearchHit hit : hits4) {
//            System.out.println(hit.getSourceAsString());
//        }
//
//        // 过滤字段
//        SearchRequest request5 = new SearchRequest();
//        request5.indices("user");
//
//        SearchSourceBuilder query5 = new SearchSourceBuilder().query(
//                QueryBuilders.matchAllQuery());
//
//        String[] includes = {};
//        String[] excludes = {"age"};
//        query5.fetchSource(includes, excludes);
//
//        request5.source(query5);
//
//        SearchResponse response5 = esClient.search(request5, RequestOptions.DEFAULT);
//
//        SearchHits hits5 = response5.getHits();
//        System.out.println(hits5.getTotalHits());
//        System.out.println(response5.getTook());
//
//        for (SearchHit hit : hits5) {
//            System.out.println(hit.getSourceAsString());
//        }
//
//        esClient.close();
//
//    }
//
//}
