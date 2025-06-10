package com.company.service;

import com.company.dto.BaseResult;
import com.company.entity.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentService {

    public BaseResult<List<Department>> getAll() throws Exception {
        try {
            List<Department> departmentList = new ArrayList<Department>();
            var department = new Department();
            department.setId(1);
            department.setName("IT");
            department.setDescription("IT Department");

            departmentList.add(department);
            departmentList.add(department);
            departmentList.add(department);

            return new BaseResult<List<Department>>(true, departmentList, "");
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

    public BaseResult<Department> getByGuid() throws Exception {
        try {
            var department = new Department();
            department.setId(1);
            department.setName("IT");
            department.setDescription("IT Department");

            return new BaseResult<Department>(true, department, "");
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

}
