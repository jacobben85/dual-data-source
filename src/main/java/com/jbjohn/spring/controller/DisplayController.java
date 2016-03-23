package com.jbjohn.spring.controller;

import com.jbjohn.spring.objects.Employee;
import com.jbjohn.spring.objects.SearchRecord;
import com.jbjohn.spring.repositories.EmployeeSearch;
import com.jbjohn.spring.repositories.EmployeeStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

/**
 * Display controller
 */
@SuppressWarnings("unused")
@Controller
public class DisplayController {

    @Autowired
    private EmployeeStorage repo;

    @Autowired
    private EmployeeSearch search;

    // webjar demo
    @RequestMapping("/")
    String index() {
        return "default";
    }

    // add record
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    String employee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee";
    }

    // store and display
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    String employeeResult(@ModelAttribute Employee employee, Model model) {
        repo.save(employee);
        model.addAttribute("employee", employee);
        return "result";
    }

    // listing
    @RequestMapping("/employees")
    String employeeResult(Model model) {
        Iterable<Employee> list = repo.findAll();

        ArrayList<Employee> employees = new ArrayList<>();

        if (repo.count() > 0) {
            for (Employee employee : list) {
                employees.add(employee);
            }
        }

        model.addAttribute("employees", employees);
        return "list";
    }

    // listing
    @RequestMapping("/search")
    String employeeSearch(Model model) {
        Iterable<SearchRecord> list = search.findAll();

        ArrayList<SearchRecord> results = new ArrayList<>();

        if (repo.count() > 0) {
            for (SearchRecord employee : list) {
                results.add(employee);
            }
        }

        model.addAttribute("results", results);
        return "list";
    }
}