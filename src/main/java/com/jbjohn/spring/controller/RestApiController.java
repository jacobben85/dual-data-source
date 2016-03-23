package com.jbjohn.spring.controller;

import com.jbjohn.spring.objects.Employee;
import com.jbjohn.spring.objects.EmployeeRecord;
import com.jbjohn.spring.repositories.EmployeeSearch;
import com.jbjohn.spring.repositories.EmployeeStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.HashMap;

/**
 * Rest controller
 * <p>
 * I am sticking with json for the demo
 * produces=MediaType.APPLICATION_XML_VALUE could generate xml
 */
@RestController
@RequestMapping(value = "/rest", produces = {"application/json"})
public class RestApiController {

    // auto wiring spring magic
    @Autowired
    private EmployeeStorage repo;

    @Autowired
    private EmployeeSearch search;

    @RequestMapping("")
    public Map<String, Object> home() {

        Map<String, Object> model = new HashMap<>();
        model.put("title", "Spring gradle");

        return model;
    }

    @RequestMapping("/employees")
    public HashMap<String, Employee> employeeResult() {
        Iterable<Employee> list = repo.findAll();

        HashMap<String, Employee> employeeList = new HashMap<>();

        if (repo.count() > 0) {
            for (Employee employee : list) {
                employeeList.put(employee.getId(), employee);
            }
        }

        return employeeList;
    }

    @RequestMapping("/search")
    public HashMap<String, EmployeeRecord> employeeSearch() {
        Iterable<EmployeeRecord> list = search.findAll();

        HashMap<String, EmployeeRecord> employeeList = new HashMap<>();

        if (search.count() > 0) {
            for (EmployeeRecord employee : list) {
                employeeList.put(employee.getId(), employee);
            }
        }

        return employeeList;
    }

    @RequestMapping("/test")
    public Employee employeeTest() {
        Employee employee = repo.findFirstByDisplayNameIsNull();

        return employee;
    }
}
