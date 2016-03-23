package com.jbjohn.spring.batch;

import com.jbjohn.spring.objects.Employee;
import com.jbjohn.spring.objects.EmployeeRecord;
import com.jbjohn.spring.repositories.EmployeeSearch;
import com.jbjohn.spring.repositories.EmployeeStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Batch updater
 */
public class BatchUpdater implements ItemWriter<Employee> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Employee.class);

    @Autowired
    private EmployeeStorage repo;

    @Autowired
    private EmployeeSearch search;

    @Override
    public void write(List<? extends Employee> items) throws Exception {

        for (Employee employee : items) {
            boolean updated = false;
            if (employee.getDisplayName() == null) {
                updated = employee.updateDisplayName();
            }

            if (updated) {
                repo.save(employee);
                LOGGER.info("Updated : " + employee.toString());

                EmployeeRecord record = search.findOne(employee.getId());
                if (record == null) {
                    record = new EmployeeRecord(employee);
                } else {
                    record.EmployeeRecord(employee);
                }
                search.save(record);
            }
        }
    }
}