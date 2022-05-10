package com.bgagnonadam.telephony.ws.infrastructure.contact;

import java.util.List;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.bgagnonadam.telephony.ws.api.contact.dto.ContactDto;
import com.bgagnonadam.telephony.ws.client.ContactApi;

import jersey.repackaged.com.google.common.collect.Lists;

public class ContactRestClient implements ContactApi{

  private WebTarget contactWs;

  public ContactRestClient(String contactWebServiceUrl) {
    // TODO Auto-generated constructor stub
  }

  @Override
  public Response findAll() {
    // TODO should use the contactWs to fetch all contacts
    return Response.ok().build();
  }

  @Override
  public Response findById(String id) {
    // TODO should use the contactWs to find the contact with ID
    return Response.ok().build();
  }

  @Override
  public void update(ContactDto contact) {
    // TODO should use the contactWs to update this contact
  }

  @Override
  public void save(ContactDto contact) {
    // TODO should use the contactWs to create contact
  }

  @Override
  public void remove(String id) {
    // TODO should use the contactWs to remove contact
  }
}
