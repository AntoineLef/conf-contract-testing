package com.bgagnonadam.contact.ws.api;


import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.bgagnonadam.contact.ws.api.dto.ContactDto;
import com.bgagnonadam.contact.ws.domain.ContactNotFoundException;
import com.bgagnonadam.contact.ws.domain.ContactService;

public class ContactResourceImpl implements ContactResource {

  private ContactService contactService;

  public ContactResourceImpl(ContactService contactService) {
    this.contactService = contactService;
  }

  @Override
  public List<ContactDto> getContacts() {
    return contactService.findAllContacts();
  }

  @Override
  public ContactDto getContact(String id) {
    return contactService.findContact(id);
  }

  @Override
  public void addContact(ContactDto contactDto) {
    contactService.addContact(contactDto);
  }

  @Override
  public void updateContact(String id, ContactDto contactDto) {
    try {
      contactService.updateContact(id, contactDto);
    } catch (ContactNotFoundException e) {
      throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                                                .entity(e.getMessage())
                                                .build());
    }
  }

  @Override
  public void deleteContact(String id) {
    try {
      contactService.deleteContact(id);
    } catch (ContactNotFoundException e) {
      throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                                               .entity(e.getMessage())
                                               .build());
   }
  }
}
