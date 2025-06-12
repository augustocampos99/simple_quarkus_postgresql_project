package com.company.controller;

import com.company.dto.DepartmentRequestDto;
import com.company.entity.Department;
import com.company.service.DepartmentService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/departments")
public class DepartmentController {

    @Inject
    private DepartmentService departmentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        try {
            return Response.status(Response.Status.OK)
                    .entity(this.departmentService.getAll().getResult())
                    .build();
        }
        catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        try {
            var departmentResult = this.departmentService.getById(id).getResult();

            if(departmentResult == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.status(Response.Status.OK)
                    .entity(departmentResult)
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
    public Response create(DepartmentRequestDto departmentRequest) {
        try {
            var department = new Department();
            department.setName(departmentRequest.getName());
            department.setDescription(departmentRequest.getDescription());

            return Response.status(Response.Status.CREATED)
                    .entity(this.departmentService.create(department).getResult())
                    .build();
        }
        catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, DepartmentRequestDto departmentRequest) {
        try {
            var departmentResult = this.departmentService.getById(id).getResult();

            if(departmentResult == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            var department = new Department();
            department.setName(departmentRequest.getName());
            department.setDescription(departmentRequest.getDescription());

            return Response.status(Response.Status.CREATED)
                    .entity(this.departmentService.update(department, id).getResult())
                    .build();
        }
        catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("id") int id) {
        try {
            var departmentResult = this.departmentService.remove(id);

            if(departmentResult.isSuccess() == false) {
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
