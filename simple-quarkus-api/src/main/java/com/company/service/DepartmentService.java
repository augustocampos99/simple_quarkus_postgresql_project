package com.company.service;

import com.company.dto.BaseResult;
import com.company.entity.Department;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DepartmentService {

    @Inject
    private EntityManager entityManager;

    public BaseResult<List<Department>> getAll() throws Exception {
        try {
            String jpql = "SELECT d FROM " + Department.class.getName() + " d";
            List<Department> departmentList = this.entityManager.createQuery(jpql).getResultList();

            return new BaseResult<List<Department>>(true, departmentList, "");
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public BaseResult<Department> getById(int id) throws Exception {
        try {
            String jpql = "SELECT d FROM " + Department.class.getName() + " d WHERE id = :idValue";
            Department department = (Department) this.entityManager.createQuery(jpql)
                    .setParameter("idValue", id)
                    .getSingleResult();

            return new BaseResult<Department>(true, department, "");
        }
        catch(NoResultException e) {
            return new BaseResult<Department>(false, null, "No result found");
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

    @Transactional
    public BaseResult<Department> create(Department department) throws Exception {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(department);
            this.entityManager.getTransaction().commit();
            return new BaseResult<Department>(true, department, "");
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

}
