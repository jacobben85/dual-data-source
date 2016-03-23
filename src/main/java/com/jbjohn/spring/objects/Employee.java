package com.jbjohn.spring.objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 */
@Entity
public class Employee {

    private static final Logger LOGGER = LoggerFactory.getLogger(Employee.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id; // auto generated field
    private String firstName; // to be added
    private String lastName; // to be added

    private String displayName; // batch calculation first name + last name

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean updateDisplayName() {
        boolean updated = false;
        if (this.lastName != null && this.firstName != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.firstName);
            sb.append(" ");
            sb.append(this.lastName);

            if (!sb.toString().equals(this.displayName)) {
                this.displayName = sb.toString();
                updated = true;
            }
        }
        return updated;
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