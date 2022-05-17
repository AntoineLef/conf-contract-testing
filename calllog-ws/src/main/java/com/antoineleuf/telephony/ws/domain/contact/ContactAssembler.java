package com.antoineleuf.telephony.ws.domain.contact;

import com.antoineleuf.telephony.ws.api.dto.ContactDto;

public class ContactAssembler {
  

  public ContactDto create(Contact caller) {
    ContactDto contactDto = new ContactDto();
    
    contactDto.telephoneNumber = caller.getTelephoneNumber();
    
    return contactDto;
  }
}
