package com.bgagnonadam.telephony.ws.api.calllog;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/telephony/calllogs")
public interface CallLogResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  Response getCallLogs();

  @DELETE
  @Path("{id}")
  void deleteCallLog(@PathParam("id") String id);
}
