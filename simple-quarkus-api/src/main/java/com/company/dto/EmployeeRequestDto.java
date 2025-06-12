package com.company.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class EmployeeRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "CPF is required")
    private String cpf;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;

    @Min(message = "ID Department is required", value = 1)
    private int idDepartment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }
}
