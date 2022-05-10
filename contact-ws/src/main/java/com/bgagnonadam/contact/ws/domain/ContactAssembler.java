package com.bgagnonadam.contact.ws.domain;

import com.bgagnonadam.contact.ws.api.dto.ContactDto;

public class ContactAssembler {
  public Contact create(ContactDto contactDto) {
    Contact contact = new Contact();
    
    contact.setAddress(contactDto.address);
    contact.setTelephoneNumber(contactDto.telephoneNumber);
    contact.setName(contactDto.name);
    contact.setBirthday(contactDto.birthdate);
    
    return contact;
  }

  public ContactDto create(Contact contact) {
    ContactDto contactDto = new ContactDto();
    
    contactDto.address = contact.getAddress();
    contactDto.telephoneNumber = contact.getTelephoneNumber();
    contactDto.name = contact.getName();
    contactDto.id = contact.getId();
    contactDto.birthdate = contact.getBirthday();
    
    return contactDto;
  }
}
