package com.bgagnonadam.contact.ws.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bgagnonadam.contact.ws.domain.Contact;
import com.bgagnonadam.contact.ws.domain.ContactNotFoundException;
import com.bgagnonadam.contact.ws.domain.ContactRepository;

import jersey.repackaged.com.google.common.collect.Lists;

public class ContactRepositoryInMemory implements ContactRepository {

  private Map<String, Contact> contacts = new HashMap<>();

  @Override
  public List<Contact> findAll() {
      return Lists.newArrayList(contacts.values());
  }

  @Override
  public Contact findById(String id) {
    return contacts.get(id);
  }

  @Override
  public void update(Contact contact)
          throws ContactNotFoundException {
    Contact foundContact = contacts.get(contact.getId());
    if (foundContact != null) {
      contacts.put(contact.getId(), contact);
    } else {
      throw new ContactNotFoundException("Contact not found, cannot be updated");
    }
  }

  @Override
  public void save(Contact contact) {
    contacts.put(contact.getId(), contact);
  }

  @Override
  public void remove(String id) throws ContactNotFoundException {
    if(!contacts.containsKey(id)){
      throw new ContactNotFoundException("Contact not found, cannot be deleted");
    }
    contacts.remove(id);
  }
}
