package com.bgagnonadam.calllog.ws.http;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.bgagnonadam.calllog.ws.domain.UnableToRemoveCallLogException;


@Provider
public class UnableToRemoveCallLogExceptionMapper implements ExceptionMapper<UnableToRemoveCallLogException> {
  @Override
  public Response toResponse(UnableToRemoveCallLogException exception) {
    return Response.status(Status.BAD_REQUEST).build();
  }
}
