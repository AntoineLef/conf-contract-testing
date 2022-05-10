package com.bgagnonadam.telephony.ws.client;

import javax.ws.rs.core.Response;

public interface CallLogApi {

  Response findAll();

  void remove(String id) ;

}
