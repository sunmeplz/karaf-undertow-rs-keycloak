package org.github.sunmeplz.task;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import io.swagger.annotations.Api;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.feature.Features;
import org.osgi.service.component.annotations.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

//@Component//
//(//
//    immediate = true, //
//    name = "TaskResource", //
//    property = //
//    { //
//      "service.exported.interfaces=*", //
//      "service.exported.configs=org.apache.cxf.rs", //
//      "org.apache.cxf.rs.address=/tasks", //
//      // By default CXF will favor the default json provider
//      "cxf.bus.prop.skip.default.json.provider.registration=true"
//    } //
//)
//@Component(service=TaskResourceImpl.class, property={"service.exported.interfaces=*",
//        "service.exported.configs=org.apache.cxf.rs",
//        "org.apache.cxf.rs.address=/tasks"})
@Api(value = "tasks")
@Features(classes = {LoggingFeature.class})
public class TaskResourceImpl implements TaskResource/*, IntentsProvider */{
    Map<Integer, Task> taskMap;

    public TaskResourceImpl() {
        taskMap = new HashMap<Integer, Task>();
        Task task = new Task();
        task.setId(1);
        task.setTitle("Buy some coffee");
        task.setDescription("Take the extra strong");
        add(task);
        task = new Task();
        task.setId(2);
        task.setTitle("Finish DOSGi example");
        task.setDescription("");
        add(task);
    }

    public Task get(Integer id) {
        return taskMap.get(id);
    }

    public void add(Task task) {
        taskMap.put(task.getId(), task);
    }

    public void update(Integer id, Task task) {
        taskMap.put(id, task);
    }

    public Task[] getAll() {
        return taskMap.values().toArray(new Task[]{});
    }

    public void delete(Integer id) {
        taskMap.remove(id);
    }

    public List<?> getIntents() {
        return asList(new JacksonJaxbJsonProvider());
    }

}