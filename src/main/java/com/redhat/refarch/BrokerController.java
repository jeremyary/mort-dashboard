package com.redhat.refarch;

import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.definition.ProcessDefinition;
import org.kie.server.api.model.instance.ProcessInstance;
import org.kie.server.api.model.instance.TaskSummary;
import org.kie.server.api.model.instance.WorkItemInstance;
import org.kie.server.client.*;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

/***
 * @author jary
 * @since Nov/03/2016
 */
@Path("/broker")
@RequestScoped
public class BrokerController {

    private static final String URL = "http://library-bpmsuitetest2.bxms.ose/kie-server/services/rest/server";
    private static final String USER = "kieserver";
    private static final String PASSWORD = "kieserver1!";

    private static final MarshallingFormat FORMAT = MarshallingFormat.JSON;

    @GET
    @Path("/tasks")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TaskSummary> listTasks() {

        List<TaskSummary> tasks = new LinkedList<>();

        try {
            KieServicesClient client = initClient();

            UserTaskServicesClient queryClient = client.getServicesClient(UserTaskServicesClient.class);

            tasks = queryClient.findTasksAssignedAsPotentialOwner("kieserver", 0, 100);

        } catch (Exception e) {
            throw e;
        }

        return tasks;
    }

    private KieServicesClient initClient() {

        KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(URL, USER, PASSWORD);
        conf.setMarshallingFormat(FORMAT);
        return KieServicesFactory.newKieServicesClient(conf);
    }
}
