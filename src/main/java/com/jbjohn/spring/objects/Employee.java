package com.jbjohn.spring.objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;

/**
 *
 */
@Entity
public class Employee extends StorageObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(Employee.class);

    private String firstName; // to be added
    private String lastName; // to be added

    private String displayName; // batch calculation first name + last name

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

}