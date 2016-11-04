package com.redhat.refarch;

import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieContainerResourceList;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/***
 * @author jary
 * @since Nov/03/2016
 */
@Path("/home")
@RequestScoped
public class HomeController {

    private static final String URL = "http://library-bpmsuitetest2.bxms.ose/kie-server/services/rest/server";
    private static final String USER = "kieserver";
    private static final String PASSWORD = "kieserver1!";

    private static final MarshallingFormat FORMAT = MarshallingFormat.JSON;

    private KieServicesConfiguration conf;
    private KieServicesClient kieServicesClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<KieContainerResource> get() {

        conf = KieServicesFactory.newRestConfiguration(URL, USER, PASSWORD);
        conf.setMarshallingFormat(FORMAT);
        kieServicesClient = KieServicesFactory.newKieServicesClient(conf);

        KieContainerResourceList containers = kieServicesClient.listContainers().getResult();
        if (containers != null) {
            for (KieContainerResource kieContainerResource : containers.getContainers()) {
                System.out.println("\t######### Found container " + kieContainerResource.getContainerId());
            }
        }
        return containers.getContainers();
    }
}
