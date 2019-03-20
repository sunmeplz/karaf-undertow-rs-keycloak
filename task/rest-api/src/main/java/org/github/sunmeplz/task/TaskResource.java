package org.github.sunmeplz.task;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;

@Api(tags = {"tasks"})
@Path("")
@Consumes({"application/json"})
@Produces({"application/json"})
public interface TaskResource {

    @ApiOperation(value = "Get task by ID", notes = "Returns a single task", response = Task.class)
    @ApiResponses(value = {
                           @ApiResponse(code = 404, message = "Task not found")
    })
    @GET
    @Path("/{id}")
    Task get(@PathParam("id") Integer id);

    @ApiOperation(value = "Add task")
    @POST
    void add(Task task);

    @ApiOperation(value = "Update existing task")
    @PUT
    @Path("/{id}")
    void update(Integer id, Task task);

    @ApiOperation(value = "Deletes a task")
    @ApiResponses(value = {
                           @ApiResponse(code = 404, message = "Task not found")
    })
    @DELETE
    @Path("/{id}")
    void delete(Integer id);

    @ApiOperation(value = "Retrieve all tasks")
    @GET
    Task[] getAll();
}