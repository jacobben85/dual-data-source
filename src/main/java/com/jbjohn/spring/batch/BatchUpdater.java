package com.jbjohn.spring.batch;

import com.jbjohn.spring.objects.Employee;
import com.jbjohn.spring.objects.SearchRecord;
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

                SearchRecord record = search.findOneByHash(employee.getHash().toString());
                if (record == null) {
                    record = new SearchRecord(employee);
                } else {
                    record.SearchRecord(employee);
                }
                search.save(record);
            }
        }
    }
}