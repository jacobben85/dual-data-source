package com.jbjohn.spring.repositories;

import com.jbjohn.spring.objects.SearchRecord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 */
public interface EmployeeSearch extends ElasticsearchRepository<SearchRecord, String> {
    SearchRecord findOneByHash(String hash);
}
