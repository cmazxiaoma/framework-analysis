//package com.cmazxiaoma.elasticsearch;
//
//import com.google.common.collect.Maps;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequestBuilder;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.search.SearchType;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.action.update.UpdateResponse;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.xcontent.XContentBuilder;
//import org.elasticsearch.common.xcontent.XContentFactory;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.index.query.RangeQueryBuilder;
//import org.elasticsearch.search.SearchHit;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
///**
// * @author cmazxiaoma
// * @version V1.0
// * @Description: TODO
// * @date 2018/10/24 14:49
// */
//@RestController
//@RequestMapping("/es")
//@Slf4j
//public class ElasticSearchController {
//
//    public static final String BOOK_INDEX = "book";
//    public static final String BOOK_TYPE_NOVEL = "novel";
//
//    @Autowired
//    private TransportClient client;
//
//    @GetMapping("/book/novel/detail/{id}")
//    public ResultVo detail(@PathVariable(name = "id") String id) {
//        if (StringUtils.isEmpty(id)) {
//            return ResultVoGenerator.genCustomResult(ResultCode.FAIL);
//        }
//        GetResponse result = client.prepareGet(BOOK_INDEX, BOOK_TYPE_NOVEL, id).get();
//        if (!result.isExists()) {
//            return ResultVoGenerator.genCustomResult(ResultCode.FAIL);
//        }
//        return ResultVoGenerator.genSuccessResult(result.getSource());
//    }
//
//    @PostMapping("/book/novel/add")
//    public ResultVo add(@Validated(Added.class) @RequestBody Book book, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return ResultVoGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
//        }
//        try {
//            XContentBuilder contentBuilder = XContentFactory.jsonBuilder()
//                    .startObject()
//                    .field("title", book.getTitle())
//                    .field("author", book.getAuthor())
//                    .field("word_count", book.getWordCount())
//                    .field("publish_date", book.getPublishDate().getTime())
//                    .endObject();
//            IndexResponse indexResponse = client.prepareIndex(BOOK_INDEX,
//                    BOOK_TYPE_NOVEL)
//                    .setSource(contentBuilder)
//                    .get();
//            return ResultVoGenerator.genSuccessResult(indexResponse.getId());
//
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//        return ResultVoGenerator.genCustomResult(ResultCode.FAIL);
//    }
//
//    @DeleteMapping("/book/novel/delete/{id}")
//    public ResultVo delete(@PathVariable(name = "id") String id) {
//        if (StringUtils.isEmpty(id)) {
//            return ResultVoGenerator.genCustomResult(ResultCode.FAIL);
//        }
//        DeleteResponse deleteResponse = client.prepareDelete(BOOK_INDEX, BOOK_TYPE_NOVEL, id).get();
//        return ResultVoGenerator.genSuccessResult(deleteResponse.getId());
//    }
//
//    @PutMapping("/book/novel/update")
//    public ResultVo update(@Validated(Updated.class) @RequestBody Book book, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return ResultVoGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
//        }
//        try {
//            XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
//            if (!StringUtils.isBlank(book.getTitle())) {
//                builder.field("title", book.getTitle());
//            }
//            if (!StringUtils.isBlank(book.getAuthor())) {
//                builder.field("author", book.getAuthor());
//            }
//            if (!StringUtils.isBlank(book.getWordCount())) {
//                builder.field("word_count", book.getWordCount());
//            }
//            if (!Objects.isNull(book.getPublishDate())) {
//                builder.field("publish_date", book.getPublishDate().getTime());
//            }
//            builder.endObject();
//            UpdateRequest updateRequest = new UpdateRequest(BOOK_INDEX, BOOK_TYPE_NOVEL, book.getId());
//            updateRequest.doc(builder);
//            UpdateResponse updateResponse = client.update(updateRequest).get();
//            return ResultVoGenerator.genSuccessResult(updateResponse.getResult().toString());
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//        return ResultVoGenerator.genCustomResult(ResultCode.FAIL);
//    }
//
//    @PostMapping("/book/novel/list")
//    public ResultVo list(@RequestParam(name = "author", required = false) String author,
//                         @RequestParam(name = "title", required = false) String title,
//                         @RequestParam(name = "gtwordCount", required = false, defaultValue = "0") Integer gtwordCount,
//                         @RequestParam(name = "ltwordCount", required = false, defaultValue = "1000") Integer ltwordCount,
//                         @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
//                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        if (!StringUtils.isBlank(author)) {
//            boolQueryBuilder.must(QueryBuilders.matchQuery("author", author));
//        }
//        if (!StringUtils.isBlank(title)) {
//            boolQueryBuilder.must(QueryBuilders.matchQuery("title", title));
//        }
//        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("word_count");
//        if (gtwordCount >= 0 ) {
//            rangeQueryBuilder.from(gtwordCount);
//        }
//        if (ltwordCount > 0) {
//            rangeQueryBuilder.to(ltwordCount);
//        }
//        if (page <= 1) {
//            page = 0;
//        } else {
//            page = page - 1;
//        }
//        boolQueryBuilder.filter(rangeQueryBuilder);
//        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(BOOK_INDEX)
//                .setTypes(BOOK_TYPE_NOVEL)
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                .setQuery(boolQueryBuilder)
//                .setFrom(page)
//                .setSize(pageSize);
//        SearchResponse searchResponse = searchRequestBuilder.get();
//        Map<String, Object> resultMap = Maps.newHashMap();
//        resultMap.put("totalRecordCount", searchResponse.getHits().getTotalHits());
//        List<Map<String, Object>> resultList = new ArrayList<>();
//        for (SearchHit searchHit : searchResponse.getHits()) {
//            resultList.add(searchHit.getSourceAsMap());
//        }
//        resultMap.put("list", resultList);
//        return ResultVoGenerator.genSuccessResult(resultMap);
//    }
//}
