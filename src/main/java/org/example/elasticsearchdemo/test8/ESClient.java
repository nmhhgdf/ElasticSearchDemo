package org.example.elasticsearchdemo.test8;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.CreateOperation;
import co.elastic.clients.elasticsearch.indices.*;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.util.ArrayList;
import java.util.List;

public class ESClient {

    private static ElasticsearchClient client;
    private static ElasticsearchAsyncClient asyncClient;
    private static ElasticsearchTransport transport;
    private static RestClient restClient;

    private static final String INDEX_TEST = "index_test";

    public static void main(String[] args) throws Exception {
        try {
            initESConnection();
//            operationIndex();
//            operationIndexDocument();
//            operationIndexDocumentLambda();
//            queryDocument();
            queryDocumentLambda();
        } finally {
            closeConnection();
        }
    }

    private static void operationIndex() throws Exception {

        ElasticsearchIndicesClient indices = client.indices();

        BooleanResponse exists = indices.exists(new ExistsRequest.Builder()
                .index(INDEX_TEST)
                .build());
        if (exists.value()) {
            System.out.println("索引" + INDEX_TEST + "已经存在");
        } else {
            CreateIndexRequest request = new CreateIndexRequest.Builder()
                    .index(INDEX_TEST)
                    .build();
            CreateIndexResponse createIndexResponse = indices.create(request);
            System.out.println("创建索引成功：" + createIndexResponse);
        }

        GetIndexResponse getIndexResponse = indices.get(new GetIndexRequest.Builder()
                .index(INDEX_TEST)
                .build());
        System.out.println("查询响应结果：" + getIndexResponse.get(INDEX_TEST));

        DeleteIndexResponse deleteIndexResponse = indices.delete(new DeleteIndexRequest.Builder()
                .index(INDEX_TEST)
                .build());
        System.out.println("索引删除：" + deleteIndexResponse.acknowledged());


    }

    private static void operationIndexLambda() throws Exception {

        ElasticsearchIndicesClient indices = client.indices();

        BooleanResponse exists = indices.exists(
                req -> req.index(INDEX_TEST)
        );
        if (exists.value()) {
            System.out.println("索引" + INDEX_TEST + "已经存在");
        } else {
            CreateIndexResponse createIndexResponse = indices.create(
                    req -> req.index(INDEX_TEST)
            );
            System.out.println("创建索引成功：" + createIndexResponse);
        }

        GetIndexResponse getIndexResponse = indices.get(
                req -> req.index(INDEX_TEST)
        );
        System.out.println("查询响应结果：" + getIndexResponse.get(INDEX_TEST));

        DeleteIndexResponse deleteIndexResponse = indices.delete(
                req -> req.index(INDEX_TEST)
        );
        System.out.println("索引删除：" + deleteIndexResponse.acknowledged());

    }


    private static void operationIndexDocument() throws Exception {

//        CreateResponse createResponse = client.create(new CreateRequest.Builder<User>()
//                .index(INDEX_TEST)
//                .id("1001")
//                .document(new User(
//                        1001,
//                        "zhangsan",
//                        30))
//                .build());
//        System.out.println("文档创建：" + createResponse);

        List<BulkOperation> opts = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            CreateOperation<User> optObj = new CreateOperation.Builder<User>()
                    .index(INDEX_TEST)
                    .id("200" + i)
                    .document(new User(
                            2000 + i,
                            "zhangsan" + i,
                            30 + i))
                    .build();
            opts.add(new BulkOperation.Builder()
                    .create(optObj)
                    .build());
        }

        BulkResponse bulkResponse = client.bulk(
                new BulkRequest.Builder()
                        .index(INDEX_TEST)
                        .operations(opts)
                        .build()
        );
        System.out.println("批量新增数据响应： " + bulkResponse);

        DeleteResponse deleteResponse = client.delete(
                new DeleteRequest.Builder()
                        .index(INDEX_TEST)
                        .id("1001")
                        .build()
        );
        System.out.println("删除数据： " + deleteResponse);

    }

    private static void operationIndexDocumentLambda() throws Exception {

        CreateResponse createResponse = client.create(
                req -> req
                        .index(INDEX_TEST)
                        .id("1001")
                        .document(new User(1001, "zhangsan", 30))
                );
        System.out.println("文档创建：" + createResponse);


        DeleteResponse deleteResponse = client.delete(
                req -> req.index(INDEX_TEST).id("1001")
        );
        System.out.println("删除数据： " + deleteResponse);

    }

    private static void queryDocument() throws Exception {
        MatchQuery matchQuery = new MatchQuery.Builder()
                .field("age").query(30)
                .build();

        Query query = new Query.Builder()
                .match(matchQuery)
                .build();

        SearchResponse<Object> searchResponse = client.search(
                new SearchRequest.Builder()
                        .query(query)
                        .build()
                , Object.class
        );
        System.out.println(searchResponse);
    }

    private static void queryDocumentLambda() throws Exception {

        SearchResponse<Object> searchResponse = client.search(
                req -> {
                    req.query(
                            q -> q.match(
                                    m -> m.field("name").query("zhangsan")
                            )
                    );
                    return req;
                },
                Object.class
        );

        System.out.println(searchResponse);
    }

    public static void initESConnection() throws Exception {

        restClient = RestClient.builder(
                        new HttpHost("192.168.75.129", 9200, "http"))
                .build();

        transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        client = new ElasticsearchClient(transport);
        asyncClient = new ElasticsearchAsyncClient(transport);

    }

    public static void closeConnection() {
        try {
            if (transport != null) {
                transport.close();
            }
        } catch (Exception e) {
            System.err.println("关闭Transport时出错: " + e.getMessage());
        }

        try {
            if (restClient != null) {
                restClient.close();
            }
        } catch (Exception e) {
            System.err.println("关闭RestClient时出错: " + e.getMessage());
        }

        client = null;
        asyncClient = null;
        transport = null;
        restClient = null;
    }

}
