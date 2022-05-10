package com.bgagnonadam.calllog.ws;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.bgagnonadam.calllog.ws.api.CallLogResource;
import com.bgagnonadam.calllog.ws.api.CallLogResourceImpl;
import com.bgagnonadam.calllog.ws.domain.CallLog;
import com.bgagnonadam.calllog.ws.domain.CallLogAssembler;
import com.bgagnonadam.calllog.ws.domain.CallLogRepository;
import com.bgagnonadam.calllog.ws.domain.CallLogService;
import com.bgagnonadam.calllog.ws.domain.contact.ContactRepository;
import com.bgagnonadam.calllog.ws.infrastructure.CallLogDevDataFactory;
import com.bgagnonadam.calllog.ws.infrastructure.CallLogRepositoryInMemory;
import com.bgagnonadam.calllog.ws.infrastructure.contact.ContactRestClient;


/**
 * RESTApi for the call log service
 *
 */
@SuppressWarnings("all")
public class CallLogWsMain 
{
   
   private static final int WS_PORT = 8081; // HTTP port on which the web service is listening

  private static final String CONTACT_WS_URL = "http://127.0.0.1:8080/api/"; // URL of the gateway web app
    
   public static boolean isDev = true; // Would be a JVM argument or in a .property file

    public static void main(String[] args)
            throws Exception {

      // Setup resources (API)
      CallLogResource callLogResource = createCallLogResource();

      // Setup API context (JERSEY + JETTY)
      ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
      context.setContextPath("/api/");
      ResourceConfig resourceConfig = ResourceConfig.forApplication(new Application() {
        @Override
        public Set<Object> getSingletons() {
          HashSet<Object> resources = new HashSet<>();
          // Add resources to context
          resources.add(callLogResource);
          return resources;
        }
      });


      ServletContainer servletContainer = new ServletContainer(resourceConfig);
      ServletHolder servletHolder = new ServletHolder(servletContainer);
      context.addServlet(servletHolder, "/*");

      // Setup static file context (WEBAPP)
      WebAppContext webapp = new WebAppContext();
      webapp.setResourceBase("src/main/webapp");
      webapp.setContextPath("/");

      // Setup http server
      ContextHandlerCollection contexts = new ContextHandlerCollection();
      contexts.setHandlers(new Handler[] { context, webapp });
      Server server = new Server(WS_PORT);
      server.setHandler(contexts);

      try {
        server.start();
        server.join();
      } finally {
        server.destroy();
      }
    }

    private static CallLogResource createCallLogResource() {
      // Setup resources' dependencies (DOMAIN + INFRASTRUCTURE)
      CallLogRepository callLogRepository = new CallLogRepositoryInMemory();

      // For development ease
      if (isDev) {
        CallLogDevDataFactory callLogDevDataFactory = new CallLogDevDataFactory();
        List<CallLog> callLogs = callLogDevDataFactory.createMockData();
        callLogs.stream().forEach(callLogRepository::save);
      }

      CallLogAssembler callLogAssembler = new CallLogAssembler();
      ContactRepository contactRepository = new ContactRestClient(CONTACT_WS_URL);
      CallLogService callLogService = new CallLogService(callLogRepository, callLogAssembler, contactRepository );

      return new CallLogResourceImpl(callLogService);
    }
}
