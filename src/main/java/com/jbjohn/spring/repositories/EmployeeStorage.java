package com.jbjohn.spring.repositories;

import com.jbjohn.spring.objects.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 */
public interface EmployeeStorage extends CrudRepository<Employee, String>{
    Employee findFirstByDisplayNameIsNull();
}
