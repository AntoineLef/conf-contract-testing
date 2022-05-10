package com.bgagnonadam.calllog.ws.infrastructure.contact;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.bgagnonadam.calllog.ws.domain.contact.Contact;
import com.bgagnonadam.calllog.ws.domain.contact.ContactRepository;

public class ContactRestClient implements ContactRepository {

  private WebTarget contactWs;

  public ContactRestClient(String url) {
    Client client = ClientBuilder.newClient();
    this.contactWs = client.target(url).path("contacts");
  }

  @Override
  public Contact getContact(String id) {
    return contactWs.path(id).request(MediaType.APPLICATION_JSON).get(Contact.class);
  }
}
