package com.bgagnonadam.calllog.ws.domain.contact;

import com.bgagnonadam.calllog.ws.api.dto.ContactDto;

public class ContactAssembler {
  

  public ContactDto create(Contact caller) {
    ContactDto contactDto = new ContactDto();
    
    contactDto.telephoneNumber = caller.getTelephoneNumber();
    
    return contactDto;
  }
}
