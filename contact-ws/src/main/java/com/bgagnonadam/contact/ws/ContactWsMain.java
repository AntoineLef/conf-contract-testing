package com.bgagnonadam.contact.ws;

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

import com.bgagnonadam.contact.ws.api.ContactResource;
import com.bgagnonadam.contact.ws.api.ContactResourceImpl;
import com.bgagnonadam.contact.ws.domain.Contact;
import com.bgagnonadam.contact.ws.domain.ContactAssembler;
import com.bgagnonadam.contact.ws.domain.ContactRepository;
import com.bgagnonadam.contact.ws.domain.ContactService;
import com.bgagnonadam.contact.ws.infrastructure.ContactDevDataFactory;
import com.bgagnonadam.contact.ws.infrastructure.ContactRepositoryInMemory;


/**
 * RESTApi for the contact service
 *
 */
@SuppressWarnings("all")
public class ContactWsMain 
{
   
   private static final int WS_PORT = 8082; // HTTP port on which the web service is listening
    
   public static boolean isDev = true; // Would be a JVM argument or in a .property file

    public static void main(String[] args)
            throws Exception {

      // Setup resources (API)
      ContactResource contactResource = createContactResource();

      // Setup API context (JERSEY + JETTY)
      ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
      context.setContextPath("/api/");
      ResourceConfig resourceConfig = ResourceConfig.forApplication(new Application() {
        @Override
        public Set<Object> getSingletons() {
          HashSet<Object> resources = new HashSet<>();
          // Add resources to context
          resources.add(contactResource);
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

    private static ContactResource createContactResource() {
      // Setup resources' dependencies (DOMAIN + INFRASTRUCTURE)
      ContactRepository contactRepository = new ContactRepositoryInMemory();

      // For development ease
      if (isDev) {
        ContactDevDataFactory contactDevDataFactory = new ContactDevDataFactory();
        List<Contact> callLogs = contactDevDataFactory.createMockData();
        callLogs.stream().forEach(contactRepository::save);
      }

      ContactAssembler contactAssembler = new ContactAssembler();
      ContactService contactService = new ContactService(contactRepository, contactAssembler);

      return new ContactResourceImpl(contactService);
    }
}
