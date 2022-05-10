package com.bgagnonadam.calllog.ws.api;

import java.util.List;

import com.bgagnonadam.calllog.ws.api.dto.CallLogDto;
import com.bgagnonadam.calllog.ws.domain.CallLogService;

public class CallLogResourceImpl implements CallLogResource {

  private CallLogService callLogService;

  public CallLogResourceImpl(CallLogService callLogService) {
    this.callLogService = callLogService;
  }

  @Override
  public List<CallLogDto> getCallLogs() {
    return callLogService.findAllCallLogs();
  }

  @Override
  public void deleteCallLog(String id) {
    callLogService.deleteCallLog(id);
  }
}
