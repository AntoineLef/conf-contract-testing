package com.bgagnonadam.telephony.ws.client;

import javax.ws.rs.core.Response;

import com.bgagnonadam.telephony.ws.api.contact.dto.ContactDto;

public interface ContactApi {

  Response findAll();

  Response findById(String id);

  void save(ContactDto contact);

  void update(ContactDto contact);

  void remove(String id);


}
