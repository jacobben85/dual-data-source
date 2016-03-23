package com.jbjohn.spring.objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 */
@Document(indexName = "record", type = "employee")
public class EmployeeRecord {

    private static final Logger LOGGER = LoggerFactory.getLogger(Employee.class);

    @Id
    private String id; // auto generated field
    private String firstName; // to be added
    private String lastName; // to be added
    private String displayName; // batch calculation first name + last name

    public EmployeeRecord() {
    }

    public EmployeeRecord(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.displayName = employee.getDisplayName();
    }

    public void EmployeeRecord(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.displayName = employee.getDisplayName();
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDisplayName() {
        return displayName;
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
