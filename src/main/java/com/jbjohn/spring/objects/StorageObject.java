package com.jbjohn.spring.objects;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Master record
 */
@Entity
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
abstract class StorageObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageObject.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id; // auto generated field

    private UUID hash;

    public StorageObject() {
        if (hash == null) {
            hash = UUID.randomUUID();
        }
    }

    public String getId() {
        return id;
    }

    public UUID getHash() {
        return hash;
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
