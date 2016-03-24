package com.jbjohn.spring.objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 */
@SuppressWarnings("unused")
@Document(indexName = "search", type = "record")
public class SearchRecord {

    private static final Logger LOGGER = LoggerFactory.getLogger(Employee.class);

    @Id
    private String hash;
    private String type;
    private StorageObject record;

    public SearchRecord() {
    }

    public SearchRecord(StorageObject record) {
        type = record.getClass().getName();
        hash = record.getHash().toString();
        this.record = record;
    }

    public void searchRecord(StorageObject record) {
        type = record.getClass().getSimpleName();
        this.record = record;
    }

    public String getHash() {
        return hash;
    }

    public String getType() {
        return type;
    }

    public StorageObject getRecord() {
        return record;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String response = "";
        try {
            response = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            LOGGER.error("Exception", e);
        }
        return response;
    }
}
