package com.antoineleuf.telephony.ws.infrastructure.contact;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.antoineleuf.telephony.ws.api.contact.dto.ContactDto;
import com.antoineleuf.telephony.ws.client.ContactApi;

public class ContactRestClient implements ContactApi {

  private WebTarget contactWs;

  public ContactRestClient(String contactWebServiceUrl) {
    Client client = ClientBuilder.newClient();
    this.contactWs = client.target(contactWebServiceUrl).path("contacts");
  }

  @Override
  public Response findAll() {
    try {
      return contactWs.request(MediaType.APPLICATION_JSON).get();
    } catch (ProcessingException exception) {
      return Response.status(Status.BAD_REQUEST).build();
    }
  }

  @Override
  public Response findById(String id) {
    return contactWs.path(id).request(MediaType.APPLICATION_JSON).get();
  }

  @Override
  public void update(ContactDto contact) {
    contactWs.path(contact.id).request().put(Entity.json(contact));
  }

  @Override
  public void save(ContactDto contact) {
    contactWs.request().post(Entity.json(contact));
  }

  @Override
  public void remove(String id) {
    contactWs.path(id).request(MediaType.APPLICATION_JSON).delete();
  }
}
