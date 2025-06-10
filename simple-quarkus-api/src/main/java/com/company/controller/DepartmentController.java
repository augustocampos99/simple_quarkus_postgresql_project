package com.company.controller;

import com.company.dto.DepartmentRequestDto;
import com.company.entity.Department;
import com.company.service.DepartmentService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/departments")
public class DepartmentController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        try {
            var departmentService = new DepartmentService();
            return Response.status(Response.Status.OK)
                    .entity(departmentService.getAll().getResult())
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
    public Response getById(@PathParam("guid") String guid) {
        try {
            var departmentService = new DepartmentService();
            return Response.status(Response.Status.OK)
                    .entity(departmentService.getByGuid().getResult())
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
            var departmentService = new DepartmentService();
            return Response.status(Response.Status.CREATED)
                    .entity(departmentService.getByGuid().getResult())
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
    public Response update(@PathParam("guid") String guid, DepartmentRequestDto departmentRequest) {
        try {
            var departmentService = new DepartmentService();
            return Response.status(Response.Status.OK)
                    .entity(departmentService.getAll().getResult())
                    .build();
        }
        catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

}
