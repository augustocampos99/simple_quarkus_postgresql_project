package com.company.controller;

import com.company.dto.EmployeeRequestDto;
import com.company.entity.Employee;
import com.company.service.EmployeeService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/api/employees")
public class EmployeeController {

    @Inject
    private EmployeeService employeeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        try {
            return Response.status(Response.Status.OK)
                    .entity(this.employeeService.getAll().getResult())
                    .build();
        }
        catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("{guid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByGuid(@PathParam("guid") UUID guid) {
        try {
            var employeeResult = this.employeeService.getByGuid(guid).getResult();

            if(employeeResult == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.status(Response.Status.OK)
                    .entity(employeeResult)
                    .build();
        }
        catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid EmployeeRequestDto employeeRequest) {
        try {
            var employee = new Employee();
            employee.setName(employeeRequest.getName());
            employee.setCpf(employeeRequest.getCpf());
            employee.setEmail(employeeRequest.getEmail());
            employee.setPhone(employeeRequest.getPhone());
            employee.setIdDepartment(employeeRequest.getIdDepartment());

            var employeeResult = this.employeeService.create(employee);

            if(!employeeResult.isSuccess()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(employeeResult.getMessage())
                        .build();
            }

            return Response.status(Response.Status.CREATED)
                    .entity(employeeResult.getResult())
                    .build();
        }
        catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("{guid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("guid") UUID guid, @Valid EmployeeRequestDto employeeRequest) {
        try {
            var employee = new Employee();
            employee.setName(employeeRequest.getName());
            employee.setCpf(employeeRequest.getCpf());
            employee.setEmail(employeeRequest.getEmail());
            employee.setPhone(employeeRequest.getPhone());
            employee.setIdDepartment(employeeRequest.getIdDepartment());

            var employeeResult = this.employeeService.update(employee, guid);

            if(!employeeResult.isSuccess()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(employeeResult.getMessage())
                        .build();
            }

            if(employeeResult.getResult() == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.status(Response.Status.OK)
                    .entity(employeeResult.getResult())
                    .build();
        }
        catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("{guid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("guid") UUID guid) {
        try {
            var employeeResult = this.employeeService.remove(guid);

            if(!employeeResult.isSuccess()) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.status(Response.Status.OK)
                    .entity(true)
                    .build();
        }
        catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

}
