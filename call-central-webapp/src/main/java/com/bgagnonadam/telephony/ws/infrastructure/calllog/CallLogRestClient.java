package com.bgagnonadam.telephony.ws.infrastructure.calllog;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.bgagnonadam.telephony.ws.client.CallLogApi;

public class CallLogRestClient implements CallLogApi {

  private WebTarget callLogWs;

  public CallLogRestClient(String url) {
    Client client = ClientBuilder.newClient();
    this.callLogWs = client.target(url).path("calllogs");
  }

  @Override
  public Response findAll() {
    try {
      return callLogWs.request(MediaType.APPLICATION_JSON).get();
    } catch (ProcessingException exception) {
      return Response.status(Status.BAD_REQUEST).build();
    }
  }

  @Override
  public void remove(String id) {
    callLogWs.path(id).request(MediaType.APPLICATION_JSON).delete();
  }
}
