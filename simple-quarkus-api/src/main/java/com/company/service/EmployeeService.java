package com.company.service;

import com.company.dto.BaseResult;
import com.company.entity.Department;
import com.company.entity.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class EmployeeService {

    @Inject
    private EntityManager entityManager;

    public BaseResult<List<Employee>> getAll() throws Exception {
        try {
            String jpql = "SELECT e FROM " + Employee.class.getName() + " e ORDER BY e.id DESC";
            List<Employee> employeeList = this.entityManager.createQuery(jpql).getResultList();

            return new BaseResult<List<Employee>>(true, employeeList, "");
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

    public BaseResult<Employee> getByGuid(UUID guid) throws Exception {
        try {
            String jpql = "SELECT e FROM " + Employee.class.getName() + " e WHERE guid = :guidValue";
            Employee employee = this.entityManager.createQuery(jpql, Employee.class)
                    .setParameter("guidValue", guid)
                    .getSingleResult();

            return new BaseResult<Employee>(true, employee, "");
        }
        catch(NoResultException e) {
            return new BaseResult<Employee>(false, null, "No result found");
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

    @Transactional
    public BaseResult<Employee> create(Employee employee) throws Exception {
        try {
            String jpql = "SELECT d FROM " + Department.class.getName() + " d WHERE d.id = :idValue";
            var departmentResult = this.entityManager.createQuery(jpql, Department.class)
                    .setParameter("idValue", employee.getIdDepartment())
                    .getSingleResult();

            if(departmentResult == null) {
                return new BaseResult<Employee>(false, null, "Department not found");
            }

            employee.setGuid(UUID.randomUUID());
            this.entityManager.persist(employee);
            return new BaseResult<Employee>(true, employee, "");
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

    @Transactional
    public BaseResult<Employee> update(Employee employee, UUID guid) throws Exception {
        try {
            String jpql = "SELECT d FROM " + Department.class.getName() + " d WHERE d.id = :idValue";
            var departmentResult = this.entityManager.createQuery(jpql, Department.class)
                    .setParameter("idValue", employee.getIdDepartment())
                    .getSingleResult();

            if(departmentResult == null) {
                return new BaseResult<Employee>(false, null, "Department not found");
            }

            String jpql2 = "SELECT e FROM "  + Employee.class.getName() + " e WHERE e.guid = :guidValue";
            var employeeResult = this.entityManager.createQuery(jpql2, Employee.class)
                    .setParameter("guidValue", guid)
                    .getSingleResult();

            if(employeeResult != null) {
                employeeResult.setName(employee.getName());
                employeeResult.setCpf(employee.getCpf());
                employeeResult.setEmail(employee.getEmail());
                employeeResult.setPhone(employee.getPhone());
                employeeResult.setIdDepartment(employee.getIdDepartment());

                this.entityManager.persist(employeeResult);
                return new BaseResult<Employee>(true, employeeResult, "");
            }

            return new BaseResult<Employee>(true, null, "No result found");
        }
        catch(NoResultException e) {
            return new BaseResult<Employee>(false, null, "No result found");
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

    @Transactional
    public BaseResult<Employee> remove(UUID guid) throws Exception {
        try {
            String jpql = "SELECT e FROM "  + Employee.class.getName() + " e WHERE e.guid = :guidValue";
            var employeeResult = this.entityManager.createQuery(jpql, Employee.class)
                    .setParameter("guidValue", guid)
                    .getSingleResult();

            if(employeeResult != null) {
                this.entityManager.remove(employeeResult);
                return new BaseResult<Employee>(true, null, "");
            }
            return new BaseResult<Employee>(false, null, "No result found");
        }
        catch(NoResultException e) {
            return new BaseResult<Employee>(false, null, "No result found");
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

}
