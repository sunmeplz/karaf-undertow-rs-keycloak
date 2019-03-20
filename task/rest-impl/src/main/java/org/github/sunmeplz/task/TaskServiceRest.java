package org.github.sunmeplz.task;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.annotations.Component;

//@Component(service=TaskServiceRest.class, property={"service.exported.interfaces=*",
//        "service.exported.configs=org.apache.cxf.rs",
//        "org.apache.cxf.rs.address=/tasklistRest"})
//@Path("")
public class TaskServiceRest {

    Map<Integer, Task> taskMap;
    @Context
    UriInfo uri;

    public TaskServiceRest() {
        taskMap = new HashMap<Integer, Task>();
        Task task = new Task();
        task.setId(1);
        task.setTitle("Buy some coffee");
        task.setDescription("Take the extra strong");
        taskMap.put(task.getId(),task);
        task = new Task();
        task.setId(2);
        task.setTitle("Finish DOSGi example");
        task.setDescription("");
        taskMap.put(task.getId(),task);
    }

    @GET
    @Path("{id}")
    public Response getTask(@PathParam("id") Integer id) {
        Task task = taskMap.get(id);
        return task == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(task).build();
    }

    @POST

    public Response addTask(Task task) {
        taskMap.put(task.getId(),task);
        URI taskURI = uri.getRequestUriBuilder().path(TaskServiceRest.class, "getTask").build(task.getId());
        return Response.created(taskURI).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Task> getTasks() {
        return taskMap.values();
    }

    @PUT
    @Path("{id}")
    public void updateTask(@PathParam("id") Integer id, Task task) {
        taskMap.put(id,task);
    }

    @DELETE
    @Path("{id}")
    public void deleteTask(@PathParam("id") Integer id) {
        taskMap.remove(id);
    }
}
