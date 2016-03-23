package com.jbjohn.spring.repositories;

import com.jbjohn.spring.objects.EmployeeRecord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 */
public interface EmployeeSearch extends ElasticsearchRepository<EmployeeRecord, String> {
}
