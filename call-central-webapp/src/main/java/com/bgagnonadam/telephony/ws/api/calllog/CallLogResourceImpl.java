package com.bgagnonadam.telephony.ws.api.calllog;

import javax.ws.rs.core.Response;

import com.bgagnonadam.telephony.ws.client.CallLogApi;

public class CallLogResourceImpl implements CallLogResource {

  private CallLogApi callLogApi;

  public CallLogResourceImpl(CallLogApi callLogApi) {
    this.callLogApi = callLogApi;
  }

  @Override
  public Response getCallLogs() {
    return callLogApi.findAll();
  }

  @Override
  public void deleteCallLog(String id) {
    callLogApi.remove(id);
  }
}
